package com.registro_contactos.Registro.Mapper;

import com.registro_contactos.Registro.DTO.Request.DetalleVentaRequest;
import com.registro_contactos.Registro.DTO.Response.DetalleVentaResponse;
import com.registro_contactos.Registro.Model.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {
    DetalleVenta toEntity(DetalleVentaRequest detalleVentaRequest);
    @Mapping(source = "detalleId", target = "id")
    @Mapping(source = "producto.codigoProducto", target = "codigoProducto")
    @Mapping(source = "producto.nombreProducto",target = "nombreProducto")
    DetalleVentaResponse toResponse(DetalleVenta detalleVenta);
}
