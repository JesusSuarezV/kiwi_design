package com.lula.springboot.service.impl;

import com.lula.springboot.entity.Codigo;
import com.lula.springboot.repository.CodigoRepository;
import com.lula.springboot.service.CodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class CodigoServiceImpl implements CodigoService {

    @Autowired
    CodigoRepository codigoRepository;
    @Override
    public Page<Codigo> obtenerCodigos(Pageable pageable) {
        return codigoRepository.findByEnabledTrueOrderByCodigoAsc(pageable);
    }

    @Override
    public boolean guardarCodigo(Codigo codigo) {
        codigo.setEnabled(true);
        codigoRepository.save(codigo);
        return true;
    }

    @Override
    public boolean eliminarCodigo(String codigo) {
        Codigo codigo1 = codigoRepository.getReferenceById(codigo);
        codigo1.setEnabled(false);
        codigoRepository.save(codigo1);
        return true;
    }

    @Override
    public String generarCodigo() {

            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            int length = 14;
            SecureRandom random = new SecureRandom();
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }

            return sb.toString();

    }
}
