package com.lula.springboot.repository;

import com.lula.springboot.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Page<Categoria> findByEnabledTrueOrderByNombreAsc(Pageable pageable);

    boolean existsByNombreIgnoreCaseAndEnabledTrue(String nombre);

    List<Categoria> findByEnabledTrue();




}
