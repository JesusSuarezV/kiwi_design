package com.lula.springboot.controller;

import com.lula.springboot.entity.Categoria;
import com.lula.springboot.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public String verCategorias(Model model, @RequestParam(defaultValue = "1") int page) {

        model.addAttribute("categorias", categoriaService.obtenerCategorias(PageRequest.of(page - 1, 3)));

        return "Categoria/ver_categorias";
    }

    @GetMapping("/Nueva_Categoria")
    public String crearCategoria() {
        return "Categoria/agregar_categoria";
    }

    @PostMapping("/Guardar_Categoria")
    public String guardarCategoria(@RequestParam("nombre") String nombre) {
        if (categoriaService.guardarCategoria(nombre)) {
            return "redirect:/Categorias?exitoAgregar";
        } else {
            return "redirect:/Categorias?errorAgregar";
        }
    }


    @GetMapping("/{id}/Editar_Categoria")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.obtenerCategoria(id);
        if (categoria.isEnabled()) {
            model.addAttribute("categoria", categoria);
            System.out.println(categoria.getNombre());
            return "Categoria/editar_categoria";
        } else return "redirect:/Categorias?errorCategoria";
    }


    @PostMapping("/{id}/Actualizar_Categoria")
    public String actualizarCategoria(@PathVariable Long id, @RequestParam("nombre") String nombre) {
        if (categoriaService.actualizarCategoria(id, nombre)) {
            return "redirect:/Categorias?exitoActualizar";
        } else {
            return "redirect:/Categorias?errorActualizar";
        }
    }

    @GetMapping("/{id}/Eliminar_Categoria")
    public String eliminarCategoria(@PathVariable Long id) {

        if (categoriaService.eliminarCategoria(id)) {
            return "redirect:/Categorias?exitoEliminar";
        } else return "redirect:/Categorias?errorEliminar";
    }


}
