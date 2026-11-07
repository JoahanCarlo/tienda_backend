package com.registro_contactos.Registro.Service;


import com.registro_contactos.Registro.DTO.Request.DetalleVentaRequest;
import com.registro_contactos.Registro.DTO.Request.VentaCancelacionRequest;
import com.registro_contactos.Registro.DTO.Request.VentaRequest;
import com.registro_contactos.Registro.DTO.Response.DetalleVentaResponse;
import com.registro_contactos.Registro.DTO.Response.VentaResponse;
import com.registro_contactos.Registro.Mapper.DetalleVentaMapper;
import com.registro_contactos.Registro.Mapper.VentaMapper;
import com.registro_contactos.Registro.Model.Cliente;
import com.registro_contactos.Registro.Model.DetalleVenta;
import com.registro_contactos.Registro.Model.Producto;
import com.registro_contactos.Registro.Model.Venta;
import com.registro_contactos.Registro.Repositorio.ClienteRepository;
import com.registro_contactos.Registro.Repositorio.DetalleVentaRepository;
import com.registro_contactos.Registro.Repositorio.ProductoRepository;
import com.registro_contactos.Registro.Repositorio.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final VentaMapper ventaMapper;
    private final DetalleVentaMapper detalleVentaMapper;

    public VentaService(VentaRepository ventaRepository, DetalleVentaRepository detalleVentaRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository, VentaMapper ventaMapper, DetalleVentaMapper detalleVentaMapper) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.ventaMapper = ventaMapper;
        this.detalleVentaMapper = detalleVentaMapper;
    }

    @Transactional
    public VentaResponse registrarVenta(VentaRequest ventaRequest){
        Cliente cliente = clienteRepository.findByDniCliente(ventaRequest.getDniCliente())
                .orElseThrow(()->new RuntimeException("Cliente no encontrado"));
        Venta venta = Venta.builder()
                .cliente(cliente)
                .fechaVenta(new Date())
                .totalVenta(0.0)
                .estado(true)
                .build();

        Venta ventaGuardada = ventaRepository.save(venta);

        Double total = 0.0;

        for (DetalleVentaRequest detalleVentaRequest : ventaRequest.getDetalleVentaRequests()){
            Producto producto = productoRepository.findByCodigoProducto(detalleVentaRequest.getCodigoProducto())
                    .orElseThrow(()->new RuntimeException("Producto no encontrado"));
            //6. Validar el stock
            if (producto.getStockProducto()<detalleVentaRequest.getCantidadVenta()) {
                throw new RuntimeException("Stock insuficiente para el producto : "+producto.getNombreProducto());
            }

            //Precio actual
            Double precio = producto.getPrecioProducto();

            Double subtotal = Math.round(precio * detalleVentaRequest.getCantidadVenta()*100.0)/100.0;

            //Se crea el detalle
            DetalleVenta detalleVenta =DetalleVenta.builder()
                    .venta(ventaGuardada)
                    .producto(producto)
                    .cantidadVenta(detalleVentaRequest.getCantidadVenta())
                    .precioVenta(precio)
                    .subtotalVenta(subtotal)
                    .build();

            detalleVentaRepository.save(detalleVenta);

            producto.setStockProducto(
                    producto.getStockProducto() - detalleVentaRequest.getCantidadVenta()
            );

            productoRepository.save(producto);

            total += subtotal;
        }
            total = Math.round(total*100.0)/100.0;

            ventaGuardada.setTotalVenta(total);

            ventaGuardada = ventaRepository.save(ventaGuardada);

        VentaResponse response = ventaMapper.toResponse(ventaGuardada);

        List<DetalleVentaResponse> detalles = detalleVentaRepository.findByVentaId(ventaGuardada.getId())
                .stream()
                .map(detalleVentaMapper::toResponse)
                .toList();

        response.setDetalleVentaResponses(detalles);

        return response;
    }

    public  List<VentaResponse> listarVentasRealizadas(){
        return ventaRepository.findAll()
                .stream()
                .map(venta -> {
                    VentaResponse ventaResponse = ventaMapper.toResponse(venta);
                    List<DetalleVentaResponse> detalleVentaResponses = detalleVentaRepository.findByVentaId(venta.getId())
                            .stream()
                            .map(detalleVentaMapper::toResponse)
                            .toList();
                    ventaResponse.setDetalleVentaResponses(detalleVentaResponses);
                    return ventaResponse;
                })
                .toList();
    }

    public VentaResponse cancelarVenta(VentaCancelacionRequest ventaCancelacionRequest){
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(ventaCancelacionRequest.getFechaVenta());

        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);

        Date fechaInicio = calendario.getTime();

        calendario.set(Calendar.HOUR_OF_DAY, 23);
        calendario.set(Calendar.MINUTE, 59);
        calendario.set(Calendar.SECOND, 59);
        calendario.set(Calendar.MILLISECOND, 999);

        Date fechaFin = calendario.getTime();

        List<Venta> ventas = ventaRepository.findByClienteDniClienteAndFechaVentaBetween(
                ventaCancelacionRequest.getDniCliente(),
                fechaInicio,
                fechaFin
        );

        if (ventas.isEmpty()){
            throw new RuntimeException(
                    "No se encontró una venta para el cliente y fecha indicada"
            );
        }

        if (ventas.size()>1){
            throw new RuntimeException(
                    "El clioente tiene varias compras en esa fecha"+
                            "Debe seleccionar una venta especifica"
            );
        }

        Venta venta = ventas.get(0);

        if (Boolean.FALSE.equals(venta.getEstado())){
            throw new RuntimeException(
                    "La venta ya se encuentra cancelada"
            );
        }

        List<DetalleVenta> detalleVentas = detalleVentaRepository.findByVentaId(venta.getId());

        for (DetalleVenta detalleVenta : detalleVentas) {
            Producto producto = detalleVenta.getProducto();
            producto.setStockProducto(
                    producto.getStockProducto()+detalleVenta.getCantidadVenta()
            );

            productoRepository.save(producto);
        }

        venta.setEstado(false);

        Venta ventaCancelada = ventaRepository.save(venta);

        VentaResponse ventaResponse = ventaMapper.toResponse(ventaCancelada);

        List<DetalleVentaResponse> detalleVentaResponses = detalleVentas.stream()
                .map(detalleVentaMapper::toResponse)
                .toList();

        ventaResponse.setDetalleVentaResponses(detalleVentaResponses);

        return ventaResponse;
    }

}
