package com.registro_contactos.Registro.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoProducto;
    private String nombreProducto;
    private Double precioProducto;
    private Integer stockProducto;
    private Boolean estadoProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorio_id")
    private Categoria categoria;
}
