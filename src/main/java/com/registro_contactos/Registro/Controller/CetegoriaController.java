package com.registro_contactos.Registro.Controller;

import com.registro_contactos.Registro.DTO.Request.CategoriaRequest;
import com.registro_contactos.Registro.DTO.Response.CategoriaResponse;
import com.registro_contactos.Registro.Service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:3005")
public class CetegoriaController {
    private final CategoriaService categoriaService;

    public CetegoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @Operation(description = "Registro de categorias correspondientes")
    public ResponseEntity<CategoriaResponse> registrarCategoria(@RequestBody CategoriaRequest categoriaRequest){
        CategoriaResponse categoriaResponse = categoriaService.registrarCategoria(categoriaRequest);
        return ResponseEntity.ok(categoriaResponse);
    }

    @GetMapping
    @Operation(description = "Lista de categorias")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategoria());
    }

    @PutMapping("/{id}")
    @Operation(description = "Actualizar categorias")
    public CategoriaResponse actualizarCategoria(@PathVariable Long id,
                                                 @RequestBody CategoriaRequest categoriaRequest){
        return categoriaService.actualizarCategoria(id,categoriaRequest);
    }
}
