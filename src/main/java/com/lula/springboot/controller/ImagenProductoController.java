package com.lula.springboot.controller;

import com.lula.springboot.entity.ImagenProducto;
import com.lula.springboot.service.ImagenProductoService;
import com.lula.springboot.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/Productos/{idProducto}/Imagenes")
public class ImagenProductoController {

    @Autowired
    ImagenProductoService imagenProductoService;

    @Autowired
    ProductoService productoService;

    @GetMapping
    public String mostrarImagenes(@PathVariable long idProducto, Model model) {
        model.addAttribute("producto", productoService.obtenerProducto(idProducto));
        model.addAttribute("imagenes", imagenProductoService.obtenerImagenes(productoService.obtenerProducto(idProducto)));
        return "Producto_Imagenes/ver_imagenes";
    }

    @GetMapping("/Nueva_Imagen")
    public String agregarImagen(@PathVariable long idProducto, Model model) {
        List<ImagenProducto> imagenProductos = imagenProductoService.obtenerImagenes(productoService.obtenerProducto(idProducto));
        if (imagenProductos.size() > 5) return "redirect:/Productos/{idProducto}/Imagenes?errorLimite";
        else {

            model.addAttribute("producto", productoService.obtenerProducto(idProducto));
            return "Producto_Imagenes/agregar_imagen";
        }
    }

    @PostMapping("/Guardar_Imagen")
    public String guardarImagen(@PathVariable long idProducto, @RequestParam("imagen") MultipartFile imagen) {
        List<ImagenProducto> imagenProductos = imagenProductoService.obtenerImagenes(productoService.obtenerProducto(idProducto));
        if (imagenProductos.size() > 5) return "redirect:/Productos/{idProducto}/Imagenes?errorLimite";
        try {
            ImagenProducto imagenProducto = new ImagenProducto();
            imagenProducto.setImagen(imagen.getBytes());
            imagenProducto.setProducto(productoService.obtenerProducto(idProducto));
            imagenProductoService.guardarImagen(imagenProducto);
            return "redirect:/Productos/{idProducto}/Imagenes?exitoAgregar";
        } catch (Exception e) {

            return "redirect:/Productos/{idProducto}/Imagenes?errorImagen";


        }
    }

    @GetMapping("/{id}/Eliminar_Imagen")
    public String eliminarImagen(@PathVariable long id) {

        if (imagenProductoService.eliminarImagen(id)) {
            return "redirect:/Productos/{idProducto}/Imagenes?exitoEliminar";

        }
        return "redirect:/Productos/{idProducto}/Imagenes?errorEliminar";

    }

}


