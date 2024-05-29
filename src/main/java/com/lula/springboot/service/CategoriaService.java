package com.lula.springboot.service;

import com.lula.springboot.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {

    public Page obtenerCategorias(Pageable pageable);

    public boolean guardarCategoria(String nombre);

    public boolean actualizarCategoria(long id, String nombre);

    public Categoria obtenerCategoria (long id);

    public boolean eliminarCategoria(long id);

}
