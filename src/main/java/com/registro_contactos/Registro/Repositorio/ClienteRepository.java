package com.registro_contactos.Registro.Repositorio;

import com.registro_contactos.Registro.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByEstadoClienteTrue();
    Optional<Cliente> findByDniCliente(String dniCliente);
}
