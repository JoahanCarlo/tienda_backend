package com.registro_contactos.Registro.Repositorio;

import com.registro_contactos.Registro.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    List<Login> findByEstadoFalse();
    Optional<Login> findByUsuario(String usuario);
}
