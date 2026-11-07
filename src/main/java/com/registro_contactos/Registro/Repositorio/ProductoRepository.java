package com.registro_contactos.Registro.Repositorio;

import com.registro_contactos.Registro.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByEstadoProductoTrue();
    List<Producto> findByEstadoProductoFalse();
    Optional<Producto> findByCodigoProducto(String codigoProducto);
}
