package com.lula.springboot.controller;

import com.lula.springboot.entity.Usuario;
import com.lula.springboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Registrarse")
public class RegistroController {

    @Autowired
    UsuarioService usuarioService;
    @GetMapping
    public String verRegistro(){
        return "Registro/ver_registro";
    }

    @PostMapping("/Registrar_Usuario")
    public String registrarUsuario(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("celular") long celular, @RequestParam("correo") String correo, @RequestParam("clave") String clave) {
        if (usuarioService.obtenerUsuarioPorCorreo(correo)!=null) return "redirect:/Registrarse?error";
        else{
            System.out.println("para comprobar señores");
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCelular(celular);
            usuario.setCorreo(correo);
            usuario.setClave(clave);
            usuarioService.registrarUsuario(usuario);
            return "redirect:/Iniciar_Sesion?exitoRegistro";
        }

    }

}
