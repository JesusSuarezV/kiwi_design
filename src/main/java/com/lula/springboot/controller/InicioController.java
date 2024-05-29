package com.lula.springboot.controller;

import com.lula.springboot.service.SesionService;
import com.lula.springboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @Autowired
    SesionService sesionService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/Iniciar_Sesion")
    public String verLogin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "Inicio/iniciar_sesion";
        }


        return "redirect:/";
    }

    @GetMapping("/logout")
    public String cerrarSesion(){
        return "redirect:/Iniciar_Sesion?logout";
    }

    @GetMapping
    public String verInicio(){
        if(usuarioService.obtenerUsuarioPorCorreo(sesionService.getUsernameFromSession()).getRole().getId() == 7){
            return null;
        }
        else return "redirect:/Dashboard";
    }

    @GetMapping("/Dashboard")
    public String verDashboard(){
        return "Inicio/ver_dashboard";
    }


}
