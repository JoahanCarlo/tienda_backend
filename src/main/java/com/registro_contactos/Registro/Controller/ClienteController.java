package com.registro_contactos.Registro.Controller;

import com.registro_contactos.Registro.DTO.Request.ClienteRequest;
import com.registro_contactos.Registro.DTO.Response.ClienteResponse;
import com.registro_contactos.Registro.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3005")
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(description = "Registrar cliente")
    public ResponseEntity<ClienteResponse> registrarCliente(@RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.registrarCliente(clienteRequest);
        return ResponseEntity.ok(clienteResponse);
    }

    @GetMapping
    @Operation(description = "Listar clientes de la venta")
    public ResponseEntity<List<ClienteResponse>> listarCliente(){
        return ResponseEntity.ok(clienteService.listarClientesActivos());
    }

    @PutMapping("/{id}")
    @Operation(description = "Actualizar cliente")
    public ClienteResponse actualizarCliente(@PathVariable Long id,
                                             @RequestBody ClienteRequest clienteRequest){
        return clienteService.actualizarCliente(id,clienteRequest);
    }

    @PutMapping("/{id}/estado")
    @Operation(description = "Eliminar al cliente logicamente")
    public ResponseEntity<ClienteResponse> eliminarCliente(@PathVariable Long id){
        clienteService.desactivarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
