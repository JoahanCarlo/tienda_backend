package com.registro_contactos.Registro.Service;

import com.registro_contactos.Registro.DTO.Request.CategoriaRequest;
import com.registro_contactos.Registro.DTO.Response.CategoriaResponse;
import com.registro_contactos.Registro.Mapper.CategoriaMapper;
import com.registro_contactos.Registro.Model.Categoria;
import com.registro_contactos.Registro.Repositorio.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public CategoriaResponse registrarCategoria(CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaMapper.toEntity(categoriaRequest);
        Categoria categoriaGuardado = categoriaRepository.save(categoria);
        return  categoriaMapper.toResponse(categoriaGuardado);
    }

    public List<CategoriaResponse> listarCategoria(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toResponse)
                .toList();
    }

    public CategoriaResponse actualizarCategoria(Long id, CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Categoria no existe"));
        categoria.setNombreCategoria(categoriaRequest.getNombreCategoria());
        categoria.setDescripcionCategoria(categoriaRequest.getDescripcionCategoria());
        Categoria categoriaActualizado = categoriaRepository.save(categoria);
        return categoriaMapper.toResponse(categoriaActualizado);
    }
}
