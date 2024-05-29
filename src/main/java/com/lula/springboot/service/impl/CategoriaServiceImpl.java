package com.lula.springboot.service.impl;

import com.lula.springboot.entity.Categoria;
import com.lula.springboot.repository.CategoriaRepository;
import com.lula.springboot.repository.ProductoRepository;
import com.lula.springboot.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Page obtenerCategorias(Pageable pageable) {
        return categoriaRepository.findByEnabledTrueOrderByNombreAsc(pageable);
    }

    @Override
    public boolean guardarCategoria(String nombre) {
        if (!categoriaRepository.existsByNombreIgnoreCaseAndEnabledTrue(nombre)) {
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            categoria.setEnabled(true);
            categoriaRepository.save(categoria);
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizarCategoria(long id, String nombre) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        if (!categoria.getNombre().equalsIgnoreCase(nombre)) {
            if (!categoriaRepository.existsByNombreIgnoreCaseAndEnabledTrue(nombre)) {
                categoria.setNombre(nombre);
                categoriaRepository.save(categoria);
                return true;
            }
            return false;
        }
        categoria.setNombre(nombre);
        categoriaRepository.save(categoria);
        return true;
    }

    @Override
    public Categoria obtenerCategoria(long id) {
        return categoriaRepository.getReferenceById(id);
    }

    @Override
    public boolean eliminarCategoria(long id) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        if (productoRepository.findByCategoriaAndEnabledTrue(categoria).isEmpty()){
            categoria.setEnabled(false);
            categoriaRepository.save(categoria);
            return true;
        }
        else return false;
    }


}
