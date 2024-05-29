package com.lula.springboot.service.impl;

import com.lula.springboot.entity.TokenRegistro;
import com.lula.springboot.entity.Usuario;
import com.lula.springboot.repository.TokenRegistroRepository;
import com.lula.springboot.repository.UsuarioRepository;
import com.lula.springboot.service.TokenRegistroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenRegistroServiceImpl implements TokenRegistroService {
    @Autowired
    TokenRegistroRepository tokenRegistroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public boolean activarCuenta(String token) {
        Optional<TokenRegistro> optionalTokenRegistro = tokenRegistroRepository.findById(token);

        if (optionalTokenRegistro.isEmpty()) return false;

        TokenRegistro tokenRegistro = optionalTokenRegistro.get();

        if (tokenRegistro.isEnabled()) {
            tokenRegistro.setEnabled(false);
            tokenRegistroRepository.save(tokenRegistro);
            Usuario usuario = tokenRegistro.getUsuario();
            usuario.setEnabled(true);
            usuarioRepository.save(usuario);

            return true;
        }

        return false;
    }
}
