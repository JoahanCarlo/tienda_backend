package com.registro_contactos.Registro.Repositorio;

import com.registro_contactos.Registro.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
    List<DetalleVenta> findByVentaId(Long ventaId);
}
