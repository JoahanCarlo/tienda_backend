package com.registro_contactos.Registro.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaResponse {
    private Long id;
    private String dniCliente;
    private String nombreCliente;
    private Date fechaVenta;
    private Double totalVenta;
    private String estado;
    private List<DetalleVentaResponse> detalleVentaResponses;
}