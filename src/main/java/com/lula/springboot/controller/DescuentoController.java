package com.lula.springboot.controller;

import com.lula.springboot.entity.Descuento;
import com.lula.springboot.entity.Producto;
import com.lula.springboot.service.DescuentoService;
import com.lula.springboot.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/Productos_Descuento")
public class DescuentoController {

    @Autowired
    DescuentoService descuentoService;

    @Autowired
    ProductoService productoService;

    @GetMapping()
    public String verProductos(Model model, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("productos", descuentoService.obtenerProductos(PageRequest.of(page - 1, 3)));
        model.addAttribute("service", descuentoService);
        return "Descuento/ver_productos_oferta";

    }

    @GetMapping("/{idProducto}/Descuentos")
    public String verDescuentosDeUnProducto(Model model, @PathVariable Long idProducto, @RequestParam(defaultValue = "1") int page) {

        Producto producto = productoService.obtenerProducto(idProducto);

        model.addAttribute("producto", producto);

        model.addAttribute("descuentos", descuentoService.obtenerDescuentos(producto, PageRequest.of(page - 1, 3)));

        return "Descuento/ver_descuentos";


    }

    @GetMapping("/{idProducto}/Descuentos/Nuevo_Descuento")
    public String crearDescuento(Model model, @PathVariable Long idProducto) {
        model.addAttribute("producto", productoService.obtenerProducto(idProducto));
        return "Descuento/agregar_descuento";
    }

    @PostMapping("/{idProducto}/Descuentos/Guardar_Descuento")
    public String guardarDescuento(Model model, @PathVariable Long idProducto, @RequestParam("descuento") int valor, @RequestParam("envio") boolean envio, @RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) {

        LocalDate inicio = LocalDate.parse(fechaInicio);

        LocalDate fin = LocalDate.parse(fechaFin);

        if (fin.isAfter(inicio)) {

            if (descuentoService.validarDescuento(idProducto, inicio, fin)) {

                Descuento descuento = new Descuento();
                descuento.setProducto(productoService.obtenerProducto(idProducto));
                descuento.setDescuento(valor);
                descuento.setEnvioGratis(envio);
                descuento.setInicio(inicio);
                descuento.setFin(fin);

                descuentoService.guardarDescuento(descuento);
                return "redirect:/Productos_Descuento/{idProducto}/Descuentos?exitoAgregar";

            }
            return "redirect:/Productos_Descuento/{idProducto}/Descuentos?errorDescuento";
        }
        return "redirect:/Productos_Descuento/{idProducto}/Descuentos?errorFechas";
    }

    @GetMapping("/{idProducto}/Descuentos/{id}/Editar_Descuento")
    public String editarDescuento(Model model, @PathVariable Long id) {
        model.addAttribute("descuento", descuentoService.obtenerDescuento(id));
        return "Descuento/editar_descuento";
    }

    @PostMapping("/{idProducto}/Descuentos/{id}/Actualizar_Descuento")
    public String actualizarDescuento(Model model, @PathVariable Long id, @RequestParam("descuento") int valor, @RequestParam("envio") boolean envio, @RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) {

        LocalDate inicio = LocalDate.parse(fechaInicio);

        LocalDate fin = LocalDate.parse(fechaFin);

        Descuento descuento = descuentoService.obtenerDescuento(id);

        if (fin.isAfter(inicio)) {
            if (descuento.isEnabled()) {
                if (descuentoService.validarDescuentoActualizado(descuento, inicio, fin)) {

                    descuento.setDescuento(valor);
                    descuento.setEnvioGratis(envio);
                    descuento.setInicio(inicio);
                    descuento.setFin(fin);

                    descuentoService.actualizarDescuento(descuento);
                    return "redirect:/Productos_Descuento/{idProducto}/Descuentos?exitoActualizar";

                }
                return "redirect:/Productos_Descuento/{idProducto}/Descuentos?errorDescuento";
            }
            return "redirect:/Productos_Descuento/{idProducto}/Descuentos?errorExistente";
        }
        return "redirect:/Productos_Descuento/{idProducto}/Descuentos?errorFechas";
    }

    @GetMapping("/{idProducto}/Descuentos/{id}/Eliminar_Descuento")
    public String eliminarDescuento(@PathVariable Long id) {
        if(descuentoService.eliminarDescuento(descuentoService.obtenerDescuento(id))) return "redirect:/Productos_Descuento/{idProducto}/Descuentos?exitoEliminar";
        else return "redirect:/Productos_Descuento/{idProducto}/Descuentos?errorEliminar";
    }
}
