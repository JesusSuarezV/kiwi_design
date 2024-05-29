package com.lula.springboot.controller;

import com.lula.springboot.entity.Codigo;
import com.lula.springboot.service.CodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/Codigos")
public class CodigoController {

    @Autowired
    CodigoService codigoService;

    @GetMapping
    public String verCodigos(Model model, @RequestParam(defaultValue = "1") int page) {

        model.addAttribute("codigos", codigoService.obtenerCodigos(PageRequest.of(page - 1, 3)));
        return "Codigo/ver_codigos";

    }

    @GetMapping("/Nuevo_Codigo")
    public String agregarCodigo() {
        return "Codigo/agregar_codigo";
    }

    @PostMapping("/Guardar_Codigo")
    public String guardarCodigo(@RequestParam("descuento") int valor, @RequestParam("envio") boolean envio) {
        Codigo codigo = new Codigo();
        codigo.setCodigo(codigoService.generarCodigo());
        codigo.setDescuento(valor);
        codigo.setEnvioGratis(envio);
        if (codigoService.guardarCodigo(codigo)) return "redirect:/Codigos?exitoAgregar";
        else return "redirect:/Codigos?errorAgregar";
    }

    @GetMapping("/{codigo}/Eliminar_Codigo")
    public String eliminarCodigo(@PathVariable String codigo){
        if (codigoService.eliminarCodigo(codigo)) return "redirect:/Codigos?exitoEliminar";
        else return "redirect:/Codigos?errorEliminar";
    }
}
