package com.lula.springboot.service;

import com.lula.springboot.entity.ImagenProducto;
import com.lula.springboot.entity.Producto;

import java.util.List;

public interface ImagenProductoService {

    public List<ImagenProducto> obtenerImagenes(Producto producto);

    public boolean guardarImagen (ImagenProducto imagenProducto);

    public boolean eliminarImagen (Long id);

}
