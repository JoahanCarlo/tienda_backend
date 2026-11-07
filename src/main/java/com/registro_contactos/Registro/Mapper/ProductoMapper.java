package com.registro_contactos.Registro.Mapper;

import com.registro_contactos.Registro.DTO.Request.ProductoRequest;
import com.registro_contactos.Registro.DTO.Response.ProductoResponse;
import com.registro_contactos.Registro.Model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoRequest productoRequest);

    @Mapping(source = "categoria.nombreCategoria", target = "nombreCategoria")
    @Mapping(target = "estadoProducto", expression = "java(convertirEstado(producto.getEstadoProducto()))")
    ProductoResponse toResponse(Producto producto);

    default String convertirEstado(Boolean estado){
        return Boolean.TRUE.equals(estado) ? "ACTIVO" : "INACTIVO";
    }
}
