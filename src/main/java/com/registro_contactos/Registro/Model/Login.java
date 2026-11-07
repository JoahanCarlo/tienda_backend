package com.registro_contactos.Registro.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Login")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;

    private String usuario;

    private String password;

    private Boolean estado;
}
