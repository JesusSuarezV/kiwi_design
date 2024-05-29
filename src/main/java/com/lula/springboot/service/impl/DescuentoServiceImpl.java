package com.lula.springboot.service.impl;

import com.lula.springboot.entity.Descuento;
import com.lula.springboot.entity.Producto;
import com.lula.springboot.repository.DescuentoRepository;
import com.lula.springboot.repository.ProductoRepository;
import com.lula.springboot.service.DescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DescuentoServiceImpl implements DescuentoService {
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    DescuentoRepository descuentoRepository;

    @Override
    public Page obtenerProductos(Pageable pageable) {
        return productoRepository.findByEnabledTrueOrderByNombreAsc(pageable);
    }

    @Override
    public Page obtenerDescuentos(Producto producto, Pageable pageable) {
        return descuentoRepository.findByProductoAndEnabledTrueOrderByInicioDesc(producto, pageable);
    }

    @Override
    public Descuento obtenerDescuento(Long id) {
        return descuentoRepository.getReferenceById(id);
    }

    @Override
    public boolean validarDescuento(Long idProducto, LocalDate inicio, LocalDate fin) {
        return descuentoRepository.buscarDescuentosSuperpuestos(idProducto, inicio, fin).isEmpty();
    }

    @Override
    public boolean validarDescuentoActualizado(Descuento descuento, LocalDate inicio, LocalDate fin) {

        descuento.setEnabled(false);

        descuentoRepository.save(descuento);

        boolean validacion = descuentoRepository.buscarDescuentosSuperpuestos(descuento.getProducto().getId(), inicio, fin).isEmpty();

        descuento.setEnabled(true);

        descuentoRepository.save(descuento);

        return validacion;
    }

    @Override
    public boolean guardarDescuento(Descuento descuento) {
        descuento.setEnabled(true);
        descuentoRepository.save(descuento);
        return true;
    }

    @Override
    public boolean actualizarDescuento(Descuento descuento) {
        descuentoRepository.save(descuento);
        return true;
    }

    @Override
    public boolean eliminarDescuento(Descuento descuento) {
        descuento.setEnabled(false);
        descuentoRepository.save(descuento);
        return true;
    }

    @Override
    public String obtenerPrecioActual(Long id) {

        String precio = "";

        double precioProducto = 0;

        Optional<Descuento> optionalDescuento = descuentoRepository.encontrarOfertaActual(id, LocalDate.now());

        if(optionalDescuento.isPresent()){
            Descuento descuento = optionalDescuento.get();
            precioProducto = descuento.getProducto().getPrecio() - (descuento.getProducto().getPrecio() * descuento.getDescuento() * 0.01);

            precio = Math.round(precioProducto*100d)/100d + " (" + descuento.getDescuento() + " % OFF)";

        }
        else precio = "" + productoRepository.getReferenceById(id).getPrecio();

        return precio;
    }


}
