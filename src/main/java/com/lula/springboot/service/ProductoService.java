package com.lula.springboot.service;

import com.lula.springboot.entity.Categoria;
import com.lula.springboot.entity.Producto;
import com.lula.springboot.repository.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {

    public List<Categoria> obtenerCategorias();

    public Categoria obtenerCategoria(Long id);

    public boolean guardarProducto(Producto producto);

    public Page obtenerProductos(Pageable pageable);

    public Producto obtenerProducto(Long id);

    public boolean actualizarProducto(Producto producto, String oldNombre);
    public boolean eliminarProducto(Long id);
}
