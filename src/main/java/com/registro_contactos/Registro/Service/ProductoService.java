package com.registro_contactos.Registro.Service;

import com.registro_contactos.Registro.DTO.Request.ProductoRequest;
import com.registro_contactos.Registro.DTO.Response.ProductoResponse;
import com.registro_contactos.Registro.Mapper.CategoriaMapper;
import com.registro_contactos.Registro.Mapper.ProductoMapper;
import com.registro_contactos.Registro.Model.Categoria;
import com.registro_contactos.Registro.Model.Producto;
import com.registro_contactos.Registro.Repositorio.CategoriaRepository;
import com.registro_contactos.Registro.Repositorio.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper, CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public ProductoResponse registrarProducto(ProductoRequest productoRequest){
        Producto producto = productoMapper.toEntity(productoRequest);
        Categoria categoria = categoriaRepository.findById(productoRequest.getCategoriaId())
                        .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
        producto.setCategoria(categoria);
        producto.setEstadoProducto(true);
        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.toResponse(productoGuardado);
    }

    public List<ProductoResponse> listarProducto(){
        List<Producto> productos = productoRepository.findByEstadoProductoTrue();
        return productos.stream()
                .map(productoMapper::toResponse)
                .toList();
    }

    public List<ProductoResponse> listarProductosEliminados(){
        List<Producto> productos = productoRepository.findByEstadoProductoFalse();
        return productos.stream()
                .map(productoMapper::toResponse)
                .toList();
    }

    public ProductoResponse actualizarProducto(Long id, ProductoRequest productoRequest){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setCodigoProducto(productoRequest.getCodigoProducto());
        producto.setNombreProducto(productoRequest.getNombreProducto());
        producto.setPrecioProducto(productoRequest.getPrecioProducto());
        producto.setStockProducto(productoRequest.getStockProducto());
        Categoria categoria = categoriaRepository.findById(productoRequest.getCategoriaId())
                .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
        producto.setCategoria(categoria);
        Producto productoActualizado = productoRepository.save(producto);
        return productoMapper.toResponse(productoActualizado);
    }

    public void desactivarProducto(Long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No encontrado"));
        producto.setEstadoProducto(false);
        productoRepository.save(producto);
    }
}
