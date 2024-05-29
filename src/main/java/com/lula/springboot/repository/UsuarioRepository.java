package com.lula.springboot.repository;

import com.lula.springboot.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario getUsuarioByCorreo(String correo);

    Page<Usuario> findByEnabledTrueOrderByNombreAsc(Pageable pageable);
}
