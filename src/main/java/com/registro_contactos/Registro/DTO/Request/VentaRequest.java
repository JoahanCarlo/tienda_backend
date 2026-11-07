package com.registro_contactos.Registro.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequest {
    private String dniCliente;
    private List<DetalleVentaRequest> detalleVentaRequests;
}
