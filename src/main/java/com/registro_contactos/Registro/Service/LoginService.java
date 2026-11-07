package com.registro_contactos.Registro.Service;

import com.registro_contactos.Registro.DTO.Request.LoginRequest;
import com.registro_contactos.Registro.DTO.Response.LoginResponse;
import com.registro_contactos.Registro.Mapper.LoginMapper;
import com.registro_contactos.Registro.Model.Login;
import com.registro_contactos.Registro.Repositorio.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;

    public LoginService(LoginRepository loginRepository, LoginMapper loginMapper) {
        this.loginRepository = loginRepository;
        this.loginMapper = loginMapper;
    }

    public LoginResponse registrarUsuario(LoginRequest loginRequest){
        Login login = loginMapper.toEntity(loginRequest);
        login.setEstado(true);
        Login loginGuardado = loginRepository.save(login);
        return loginMapper.toResponse(loginGuardado);
    }

    public List<LoginResponse> listarUsuarios(){
        List<Login> logins = loginRepository.findAll();
        return logins.stream()
                .map(loginMapper::toResponse)
                .toList();
    }

    public List<LoginResponse> UsuarioInactivo(Long id){
        Login login = loginRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El usuario no existe"));
        login.setEstado(false);
        loginRepository.save(login);
        List<Login> listaUsuarioInactivos = loginRepository.findByEstadoFalse();

        return listaUsuarioInactivos.stream().map(u-> {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(u.getId());
            loginResponse.setEstado(u.getEstado());
            return loginResponse;
        }).collect(Collectors.toList());
    }

    public LoginResponse actualizarUsuario(Long id, LoginRequest loginRequest){
        Login login = loginRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El usuario no existe"));
        login.setNombreCompleto(loginRequest.getNombreCompleto());
        login.setUsuario(loginRequest.getUsuario());
        login.setPassword(loginRequest.getPassword());
        Login loginActualizado = loginRepository.save(login);
        return loginMapper.toResponse(loginActualizado);
    }

    public LoginResponse desactivarUsuario(Long id){
        Login login = loginRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Usuario no encontrado"));

        login.setEstado(!Boolean.TRUE.equals(login.getEstado()));
        Login loginActualizado = loginRepository.save(login);

        return loginMapper.toResponse(loginActualizado);
    }


    public LoginResponse accederSistema(LoginRequest loginRequest){
        System.out.println("USUARIO RECIBIDO: [" + loginRequest.getUsuario() + "]");
        Login login = loginRepository.findByUsuario(loginRequest.getUsuario())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        if (!Boolean.TRUE.equals(login.getEstado())){
            throw new RuntimeException("El usuario se encuentra inactivo");
        }

        if (login.getPassword() == null || !login.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return LoginResponse.builder()
                .id(login.getId())
                .nombreCompleto(login.getNombreCompleto())
                .usuario(login.getUsuario())
                .estado(login.getEstado())
                .build();
    }
}
