package com.lula.springboot.repository;

import com.lula.springboot.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario getUsuarioByCorreo(String correo);

    Page<Usuario> findByEnabledTrueOrderByNombreAsc(Pageable pageable);

    @Query("SELECT u FROM Usuario u " +
            "WHERE u.enabled = true " +
            "AND u.role.nombre NOT IN ('ADMINISTRADOR', 'SUPERADMIN') " +
            "ORDER BY u.nombre ASC")
    Page<Usuario> findByEnabledTrueAndRoleNotAdminOrSuperAdmin(Pageable pageable);

    @Query("SELECT u FROM Usuario u " +
            "WHERE u.enabled = true " +
            "AND u.role.nombre NOT IN ('SUPERADMIN') " +
            "ORDER BY u.nombre ASC")
    Page<Usuario> findByEnabledTrueAndRoleNotSuperAdmin(Pageable pageable);

}
