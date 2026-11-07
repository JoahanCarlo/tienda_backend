package com.registro_contactos.Registro.Service;


import com.registro_contactos.Registro.DTO.Request.DetalleVentaRequest;
import com.registro_contactos.Registro.DTO.Response.DetalleVentaResponse;
import com.registro_contactos.Registro.Mapper.DetalleVentaMapper;
import com.registro_contactos.Registro.Model.DetalleVenta;
import com.registro_contactos.Registro.Model.Producto;
import com.registro_contactos.Registro.Model.Venta;
import com.registro_contactos.Registro.Repositorio.DetalleVentaRepository;
import com.registro_contactos.Registro.Repositorio.ProductoRepository;
import com.registro_contactos.Registro.Repositorio.VentaRepository;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaMapper detalleVentaMapper;


    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository, VentaRepository ventaRepository, ProductoRepository productoRepository, DetalleVentaMapper detalleVentaMapper) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.detalleVentaMapper = detalleVentaMapper;
    }

    public DetalleVentaResponse registrarDetalleVenta(Venta venta,DetalleVentaRequest detalleVentaRequest){
                Producto producto = productoRepository.findByCodigoProducto(detalleVentaRequest.getCodigoProducto())
                .orElseThrow(()->new RuntimeException("Producto no encontrado"));
        Double subtotalVen = producto.getPrecioProducto() * detalleVentaRequest.getCantidadVenta();
        DetalleVenta detalleVenta = detalleVentaMapper.toEntity(detalleVentaRequest);
        detalleVenta.setVenta(venta);
        detalleVenta.setProducto(producto);
        detalleVenta.setPrecioVenta(producto.getPrecioProducto());
        detalleVenta.setSubtotalVenta(subtotalVen);

        DetalleVenta detalleVentaGuardado = detalleVentaRepository.save(detalleVenta);
        return detalleVentaMapper.toResponse(detalleVentaGuardado);
    }

}
