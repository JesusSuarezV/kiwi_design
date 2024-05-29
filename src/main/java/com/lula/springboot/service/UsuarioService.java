package com.lula.springboot.service;

import com.lula.springboot.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    public Usuario obtenerUsuarioPorCorreo(String correo);

    public boolean registrarUsuario(Usuario usuario);

    public Page<Usuario> obtenerUsuarios(Pageable pageable);

    public boolean editarUsuario(long id);

    public boolean bloquearUsuario(long id);

    public boolean actualizarRol (long id, long idRol);

    Usuario obtenerUsuarioPorId(Long id);
}
