package com.registro_contactos.Registro.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private Long id;
    private String nombreCompleto;
    private String usuario;
    private String password;
    private Boolean estado;
}
