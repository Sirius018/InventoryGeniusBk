package com.inventorygenius.controller;

import com.inventorygenius.model.Usuario;
import com.inventorygenius.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {
    @Autowired
    private IUsuarioRepository repoUsuario;

    @PostMapping("/home/Acount")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("passwordUser") String password,
                        Model model) {
        Usuario usuario = repoUsuario.findByUsuarioAndPassword(userName, password);
        if (usuario != null) {
            return "/home/Acount";
        } else {
            model.addAttribute("error", "Usuario y/o contraseña incorrectos");
            return "loginSign";
        }
    }
}
