package com.registro_contactos.Registro.Controller;

import com.registro_contactos.Registro.DTO.Request.LoginRequest;
import com.registro_contactos.Registro.DTO.Response.LoginResponse;
import com.registro_contactos.Registro.Service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:3005")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @Operation(description = "Registrar los usuarios")
    public ResponseEntity<LoginResponse> registrarUsuario(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = loginService.registrarUsuario(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/listar")
    @Operation(description = "Lista de usuarios")
    public ResponseEntity<List<LoginResponse>> listarUsuarios(){
        return ResponseEntity.ok(loginService.listarUsuarios());
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<?> estadoUsuario(@PathVariable Long id){
        try {
            List<LoginResponse> usuariosActualizados = loginService.UsuarioInactivo(id);
            return ResponseEntity.ok(usuariosActualizados);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error al cambiar estado" +e.getMessage());
        }
    }

    @PutMapping("/{id}/estado")
    @Operation(description = "Desactivar acceso al sistema")
    public ResponseEntity<LoginResponse> desactivarUsuario(@PathVariable Long id){
        return ResponseEntity.ok(loginService.desactivarUsuario(id));
    }

    @PutMapping("/{id}")
    @Operation(description = "Actualizar usuario")
    public LoginResponse actualizarUsuario(@PathVariable Long id,
                                           @RequestBody LoginRequest loginRequest){
        return loginService.actualizarUsuario(id,loginRequest);
    }

    @PostMapping("/login")
    @Operation(description = "Acceso al sistema")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse response = loginService.accederSistema(loginRequest);
        return ResponseEntity.ok(response);
    }



}
