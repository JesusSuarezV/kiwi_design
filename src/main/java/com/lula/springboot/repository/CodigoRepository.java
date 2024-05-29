package com.lula.springboot.repository;

import com.lula.springboot.entity.Codigo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoRepository extends JpaRepository<Codigo, String> {

    Page<Codigo> findByEnabledTrueOrderByCodigoAsc(Pageable pageable);
}
