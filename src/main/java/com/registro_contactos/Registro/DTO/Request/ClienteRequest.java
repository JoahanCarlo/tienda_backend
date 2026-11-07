package com.registro_contactos.Registro.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequest {
    private String nombreCliente;
    private String apellidoCliente;
    private String dniCliente;
    private String emailCliente;
    private String telefonoCliente;
}
