package com.registro_contactos.Registro.Mapper;

import com.registro_contactos.Registro.DTO.Request.ClienteRequest;
import com.registro_contactos.Registro.DTO.Response.ClienteResponse;
import com.registro_contactos.Registro.Model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteRequest clienteRequest);
    ClienteResponse toResponse(Cliente cliente);
}
