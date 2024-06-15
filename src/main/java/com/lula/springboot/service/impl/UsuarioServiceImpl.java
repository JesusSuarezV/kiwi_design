package com.lula.springboot.service.impl;

import com.lula.springboot.entity.Role;
import com.lula.springboot.entity.TokenRegistro;
import com.lula.springboot.entity.Usuario;
import com.lula.springboot.repository.RoleRepository;
import com.lula.springboot.repository.TokenRegistroRepository;
import com.lula.springboot.repository.UsuarioRepository;
import com.lula.springboot.service.MailService;
import com.lula.springboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TokenRegistroRepository tokenRegistroRepository;

    @Autowired
    MailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.getUsuarioByCorreo(correo);
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        try {
            usuario.setRole(new Role(5, "CLIENTE", true));
            usuario.setClave(passwordEncoder.encode(usuario.getClave()));
            usuario.setEnabled(false);
            usuarioRepository.save(usuario);

            TokenRegistro tokenRegistro = new TokenRegistro();
            String token = UUID.randomUUID().toString();
            tokenRegistro.setToken(token);
            tokenRegistro.setUsuario(usuario);
            tokenRegistro.setEnabled(true);

            tokenRegistroRepository.save(tokenRegistro);

            String url = "http://kiwidesign-production.up.railway.app/Confirmacion/" + token;
            String cuerpo = "Por favor, confirme su cuenta en el siguiente enlace: <a href=\"" + url + "\">" + url + "</a>";

            mailService.enviarCorreo(usuario.getCorreo(), "ENLACE DE ACTIVACIÓN DE SU CUENTA KIWI DESIGN", cuerpo);


            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }

    }

    @Override
    public Page<Usuario> obtenerUsuariosParaAdmin(Pageable pageable) {
        return usuarioRepository.findByEnabledTrueAndRoleNotAdminOrSuperAdmin(pageable);
    }

    @Override
    public Page<Usuario> obtenerUsuariosParaSuperadmin(Pageable pageable) {
        return usuarioRepository.findByEnabledTrueAndRoleNotSuperAdmin(pageable);
    }


    @Override
    public boolean editarUsuario(long id) {
        return false;
    }

    @Override
    public boolean bloquearUsuario(long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.setEnabled(false);
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public boolean actualizarRol(long id, long idRol) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        Role role = roleRepository.getReferenceById(idRol);
        usuario.setRole(role);
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.getReferenceById(id);
    }
}
