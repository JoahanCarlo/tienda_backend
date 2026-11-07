package com.registro_contactos.Registro.DTO.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaCancelacionRequest {
    private String dniCliente;
    private Date fechaVenta;
}
