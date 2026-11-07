package com.registro_contactos.Registro.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {
    private Long id;
    private String nombreCliente;
    private String apellidoCliente;
    private String dniCliente;
    private String emailCliente;
    private String telefonoCliente;
}
