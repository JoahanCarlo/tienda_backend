package com.registro_contactos.Registro.Mapper;

import com.registro_contactos.Registro.DTO.Request.VentaRequest;
import com.registro_contactos.Registro.DTO.Response.VentaResponse;
import com.registro_contactos.Registro.Model.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VentaMapper {
    Venta toEntity(VentaRequest ventaRequest);
    @Mapping(source = "cliente.dniCliente",target = "dniCliente")
    @Mapping(source = "cliente.nombreCliente",target = "nombreCliente")
    @Mapping(target = "estado", expression = "java(Boolean.TRUE.equals(venta.getEstado()) ? \"VENDIDO\" : \"VENTA CANCELADA\")")
    VentaResponse toResponse(Venta venta);
}
