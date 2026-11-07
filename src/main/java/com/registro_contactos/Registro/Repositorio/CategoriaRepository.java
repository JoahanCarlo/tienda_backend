package com.registro_contactos.Registro.Repositorio;

import com.registro_contactos.Registro.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
