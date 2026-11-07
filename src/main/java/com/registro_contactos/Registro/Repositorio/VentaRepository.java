package com.registro_contactos.Registro.Repositorio;

import com.registro_contactos.Registro.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findByClienteDniClienteAndFechaVentaBetween(
            String dnicliente,
            Date fechaInicio,
            Date fechaFin
    );
}
