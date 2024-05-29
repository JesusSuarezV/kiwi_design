package com.lula.springboot.repository;

import com.lula.springboot.entity.Categoria;
import com.lula.springboot.entity.ImagenProducto;
import com.lula.springboot.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {

    List<ImagenProducto> findByProductoAndEnabledTrue(Producto producto);

}
