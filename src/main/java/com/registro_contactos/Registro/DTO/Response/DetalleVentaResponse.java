package com.registro_contactos.Registro.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaResponse {
    private Long id;
    private String codigoProducto;
    private String nombreProducto;
    private Integer cantidadVenta;
    private Double precioVenta;
    private Double subtotalVenta;
}
