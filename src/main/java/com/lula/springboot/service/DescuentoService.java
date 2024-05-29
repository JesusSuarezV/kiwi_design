package com.lula.springboot.service;

import com.lula.springboot.entity.Descuento;
import com.lula.springboot.entity.Producto;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface DescuentoService {

    public Page obtenerProductos(Pageable pageable);

    public Page obtenerDescuentos(Producto producto, Pageable pageable);

    public Descuento obtenerDescuento(Long id);

    public boolean validarDescuento(Long idProducto, LocalDate inicio, LocalDate fin);

    public boolean validarDescuentoActualizado( Descuento descuento, LocalDate inicio, LocalDate fin);

    public boolean guardarDescuento(Descuento descuento);

    public boolean actualizarDescuento(Descuento descuento);

    public boolean eliminarDescuento(Descuento descuento);

    public String obtenerPrecioActual(Long id);

}
