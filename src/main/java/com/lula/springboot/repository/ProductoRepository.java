package com.lula.springboot.repository;

import com.lula.springboot.entity.Categoria;
import com.lula.springboot.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaAndEnabledTrue(Categoria categoria);

    boolean existsByNombreIgnoreCaseAndEnabledTrue(String nombre);

    Page<Producto> findByEnabledTrueOrderByNombreAsc(Pageable pageable);
}
