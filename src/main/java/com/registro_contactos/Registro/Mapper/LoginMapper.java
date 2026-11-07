package com.registro_contactos.Registro.Mapper;

import com.registro_contactos.Registro.DTO.Request.LoginRequest;
import com.registro_contactos.Registro.DTO.Response.LoginResponse;
import com.registro_contactos.Registro.Model.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    Login toEntity(LoginRequest loginRequest);
    LoginResponse toResponse(Login login);
}
