package com.registro_contactos.Registro.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaRequest {
    private String nombreCategoria;
    private String descripcionCategoria;
}
