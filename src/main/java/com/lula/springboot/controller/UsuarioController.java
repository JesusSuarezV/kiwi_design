package com.lula.springboot.controller;

import com.lula.springboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public String verUsuarios(Model model, @RequestParam(defaultValue = "1") int page) {

        model.addAttribute("usuarios", usuarioService.obtenerUsuarios(PageRequest.of(page - 1, 3)));

        return "Usuario/ver_usuarios";
    }

    @GetMapping("/{id}/Editar_Rol")
    public String editarRol(@PathVariable Long id, Model model){
        model.addAttribute("usuario", usuarioService.obtenerUsuarioPorId(id));
        return "Usuario/editar_rol";
    }

    @PostMapping("/{id}/Actualizar_Rol")
    public String actualizarRol(@PathVariable Long id, @RequestParam("idRol") long idRol){

        usuarioService.actualizarRol(id, idRol);
        return "redirect:/Usuarios?exitoRol";

    }


    @GetMapping("/{id}/Bloquear_Usuario")
    public String bloquearUsuario(@PathVariable long id){

        usuarioService.bloquearUsuario(id);
        return "redirect:/Usuarios?exitoBloqueo";

    }


}
