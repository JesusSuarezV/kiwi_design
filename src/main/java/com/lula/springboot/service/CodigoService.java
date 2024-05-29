package com.lula.springboot.service;

import com.lula.springboot.entity.Codigo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CodigoService {

    public Page<Codigo> obtenerCodigos(Pageable pageable);

    public boolean guardarCodigo(Codigo codigo);

    public boolean eliminarCodigo(String codigo);

    public String generarCodigo();

}
