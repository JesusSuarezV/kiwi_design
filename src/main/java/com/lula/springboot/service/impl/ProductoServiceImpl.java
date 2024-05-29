package com.lula.springboot.service.impl;

import com.lula.springboot.entity.Categoria;
import com.lula.springboot.entity.Producto;
import com.lula.springboot.repository.CategoriaRepository;
import com.lula.springboot.repository.ProductoRepository;
import com.lula.springboot.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findByEnabledTrue();
    }

    @Override
    public Categoria obtenerCategoria(Long id) {
        return categoriaRepository.getReferenceById(id);
    }

    @Override
    public boolean guardarProducto(Producto producto) {
        if(!productoRepository.existsByNombreIgnoreCaseAndEnabledTrue(producto.getNombre())){
        producto.setEnabled(true);
        productoRepository.save(producto);
        return true;}
        else return false;
    }

    @Override
    public Page obtenerProductos(Pageable pageable) {
        return productoRepository.findByEnabledTrueOrderByNombreAsc(pageable);
    }

    @Override
    public Producto obtenerProducto(Long id) {
        return productoRepository.getReferenceById(id);
    }

    @Override
    public boolean actualizarProducto(Producto producto, String oldNombre) {
        if(!oldNombre.equalsIgnoreCase(producto.getNombre())){
            if(!productoRepository.existsByNombreIgnoreCaseAndEnabledTrue(producto.getNombre())){
                productoRepository.save(producto);
                return true;
            }
            return false;
        }

        productoRepository.save(producto);
        return true;
    }

    @Override
    public boolean eliminarProducto(Long id) {
        Producto producto = productoRepository.getReferenceById(id);
        producto.setEnabled(false);
        productoRepository.save(producto);
        return true;
    }
}
