package com.registro_contactos.Registro.Controller;

import com.registro_contactos.Registro.DTO.Request.ProductoRequest;
import com.registro_contactos.Registro.DTO.Response.ProductoResponse;
import com.registro_contactos.Registro.Service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:3005")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @Operation(description = "Registro de los productos")
    public ResponseEntity<ProductoResponse> registroProducto(@RequestBody ProductoRequest productoRequest){
        ProductoResponse productoResponse = productoService.registrarProducto(productoRequest);
        return ResponseEntity.ok(productoResponse);
    }

    @GetMapping
    @Operation(description = "Lista de productos activos")
    public ResponseEntity<List<ProductoResponse>> listaProductosActivos(){
        return ResponseEntity.ok(productoService.listarProducto());
    }

    @PutMapping("/{id}")
    @Operation(description = "Actualizar producto")
    public ProductoResponse actualizarProducto(@PathVariable Long id,
                                               @RequestBody ProductoRequest productoRequest){
        return productoService.actualizarProducto(id,productoRequest);
    }

    @PutMapping("/{id}/estado")
    @Operation(description = "Eliminar el prodcuto")
    public ResponseEntity<ProductoResponse> eliminarProducto(@PathVariable Long id){
        productoService.desactivarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
