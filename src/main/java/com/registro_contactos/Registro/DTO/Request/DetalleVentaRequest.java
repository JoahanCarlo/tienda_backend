package com.registro_contactos.Registro.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaRequest {
    private String codigoProducto;
    private Integer cantidadVenta;
}
