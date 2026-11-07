package com.registro_contactos.Registro.Mapper;

import com.registro_contactos.Registro.DTO.Request.CategoriaRequest;
import com.registro_contactos.Registro.DTO.Response.CategoriaResponse;
import com.registro_contactos.Registro.Model.Categoria;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toEntity(CategoriaRequest categoriaRequest);
    CategoriaResponse toResponse(Categoria categoria);
}
