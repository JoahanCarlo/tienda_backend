package com.registro_contactos.Registro.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {
    private String codigoProducto;
    private String nombreProducto;
    private Double precioProducto;
    private Integer stockProducto;
    private Long categoriaId;
}
