package com.registro_contactos.Registro.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponse {
    private Long id;
    private String codigoProducto;
    private String nombreProducto;
    private Double precioProducto;
    private Integer stockProducto;
    private String estadoProducto;
    private String nombreCategoria;
}
