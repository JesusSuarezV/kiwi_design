package com.lula.springboot.controller;

import com.lula.springboot.entity.Categoria;
import com.lula.springboot.entity.Producto;
import com.lula.springboot.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping()
    public String verProductos(Model model, @RequestParam(defaultValue = "1") int page) {

        model.addAttribute("productos", productoService.obtenerProductos(PageRequest.of(page - 1, 3)));

        return "Producto/ver_productos";
    }

    @GetMapping("/Nuevo_Producto")
    public String agregarProducto(Model model) {

        model.addAttribute("categorias", productoService.obtenerCategorias());
        return "Producto/agregar_producto";
    }

    @PostMapping("/Guardar_Producto")
    public String guardarProducto(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descricion, @RequestParam("categoria") Long categoriaId, @RequestParam("precio") Double precio, @RequestParam("unidades") int unidades, @RequestParam("imagenPrincipal") MultipartFile imagenPrincipal) {

        try {
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setDescripcion(descricion);
            producto.setCategoria(productoService.obtenerCategoria(categoriaId));
            producto.setPrecio(precio);
            producto.setUnidades(unidades);
            producto.setImagen(imagenPrincipal.getBytes());
            if (productoService.guardarProducto(producto)) return "redirect:/Productos?exitoAgregar";
            else return "redirect:/Productos?errorAgregar";


        } catch (Exception e) {

            return "redirect:/Productos?errorImagen";
        }


    }

    @GetMapping("/{id}/Editar_Producto")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerProducto(id);
        if (producto.isEnabled()) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", productoService.obtenerCategorias());
            System.out.println(producto.getNombre());
            return "Producto/editar_producto";
        } else return "redirect:/Productos?errorProducto";
    }

    @PostMapping("/{id}/Actualizar_Producto")
    public String actualizarProducto(@PathVariable Long id, @RequestParam("nombre") String nombre, @RequestParam("descripcion") String descricion, @RequestParam("categoria") Long categoriaId, @RequestParam("precio") Double precio, @RequestParam("unidades") int unidades, @RequestParam("imagenPrincipal") MultipartFile imagenPrincipal) {

        try {
            Producto producto = productoService.obtenerProducto(id);
            String oldNombre = producto.getNombre();
            producto.setNombre(nombre);
            producto.setDescripcion(descricion);
            producto.setCategoria(productoService.obtenerCategoria(categoriaId));
            producto.setPrecio(precio);
            producto.setUnidades(unidades);
            if (!imagenPrincipal.isEmpty()){
                System.out.println("ta vacio lol");
                producto.setImagen(imagenPrincipal.getBytes());
            }

            if (productoService.actualizarProducto(producto, oldNombre)) {
                return "redirect:/Productos?exitoActualizar";
            } else {
                return "redirect:/Productos?errorActualizar";
            }
        } catch (Exception e) {
            return "redirect:/Productos?errorImagen";
        }
    }

    @GetMapping("/{id}/Eliminar_Producto")
    public String eliminarProducto(@PathVariable Long id) {
        if (productoService.eliminarProducto(id)) {
            return "redirect:/Productos?exitoEliminar";
        } else return "redirect:/Productos?errorEliminar";
    }


}



