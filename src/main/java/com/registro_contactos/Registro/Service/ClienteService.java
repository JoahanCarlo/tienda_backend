package com.registro_contactos.Registro.Service;

import com.registro_contactos.Registro.DTO.Request.ClienteRequest;
import com.registro_contactos.Registro.DTO.Response.ClienteResponse;
import com.registro_contactos.Registro.Mapper.ClienteMapper;
import com.registro_contactos.Registro.Model.Cliente;
import com.registro_contactos.Registro.Repositorio.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepistory;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepistory, ClienteMapper clienteMapper) {
        this.clienteRepistory = clienteRepistory;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponse registrarCliente(ClienteRequest clienteRequest){
        Cliente cliente = clienteMapper.toEntity(clienteRequest);
        cliente.setEstadoCliente(true);
        Cliente clienteGuardado = clienteRepistory.save(cliente);
        return clienteMapper.toResponse(clienteGuardado);
    }

    public List<ClienteResponse> listarClientesActivos(){
        List<Cliente> clientes = clienteRepistory.findByEstadoClienteTrue();
        return clientes.stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    public ClienteResponse actualizarCliente(Long id,ClienteRequest clienteRequest){
        Cliente cliente = clienteRepistory.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setDniCliente(clienteRequest.getDniCliente());
        cliente.setNombreCliente(clienteRequest.getNombreCliente());
        cliente.setApellidoCliente(clienteRequest.getApellidoCliente());
        cliente.setTelefonoCliente(clienteRequest.getTelefonoCliente());
        cliente.setEmailCliente(clienteRequest.getEmailCliente());
        Cliente clienteActualizado = clienteRepistory.save(cliente);
        return clienteMapper.toResponse(clienteActualizado);
    }

    public void desactivarCliente(Long id){
        Cliente cliente = clienteRepistory.findById(id)
                .orElseThrow(() -> new RuntimeException(("Cliente no encontrado")));
        cliente.setEstadoCliente(false);
        clienteRepistory.save(cliente);
    }


}
