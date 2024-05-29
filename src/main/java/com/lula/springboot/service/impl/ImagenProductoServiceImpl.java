package com.lula.springboot.service.impl;

import com.lula.springboot.entity.ImagenProducto;
import com.lula.springboot.entity.Producto;
import com.lula.springboot.repository.ImagenProductoRepository;
import com.lula.springboot.service.ImagenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenProductoServiceImpl implements ImagenProductoService {

    @Autowired
    ImagenProductoRepository imagenProductoRepository;

    @Override
    public List<ImagenProducto> obtenerImagenes(Producto producto) {
        return imagenProductoRepository.findByProductoAndEnabledTrue(producto);
    }

    @Override
    public boolean guardarImagen(ImagenProducto imagenProducto) {
        imagenProducto.setEnabled(true);
        imagenProductoRepository.save(imagenProducto);

        return true;
    }

    @Override
    public boolean eliminarImagen(Long id) {
        ImagenProducto imagenProducto = imagenProductoRepository.getReferenceById(id);
        imagenProducto.setEnabled(false);
        imagenProductoRepository.save(imagenProducto);
        return true;
    }
}
