package com.lula.springboot.controller;

import com.lula.springboot.service.TokenRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TokenController {

    @Autowired
    TokenRegistroService tokenRegistroService;

    @GetMapping("/Confirmacion/{token}")
    public String activarCuenta(@PathVariable String token){
        if(tokenRegistroService.activarCuenta(token)) return "redirect:/Iniciar_Sesion?exitoActivacion";
        else return "redirect:/Iniciar_Sesion?errorToken";
    }
}
