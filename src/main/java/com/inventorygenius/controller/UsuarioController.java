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
            return "Home_Acount";
        } else {
            model.addAttribute("mensaje", "Usuario y/o contraseña incorrectos");
            model.addAttribute("clase", "alert alert-danger");
            return "loginSign";
        }
    }




    @PostMapping("/home/NewAcount/Create")
    public String registerNewUsuario(@RequestParam("userName") String userName,
                                     @RequestParam("passwordUser") String password,
                                     Model model) {
        // Verifica si ya existe un usuario con el mismo nombre de usuario
        if (repoUsuario.findByUsuario(userName) != null) {
            model.addAttribute("mensaje", "Ya existe un usuario con el mismo nombre de usuario");
            model.addAttribute("clase", "alert alert-danger");
            return "loginCreate";
        }

        // Crea un nuevo objeto Usuario y guarda los datos en la base de datos
        Usuario usuario = new Usuario();
        usuario.setUsuario(userName);
        usuario.setPassword(password);
        repoUsuario.save(usuario);
        model.addAttribute("mensaje", "Operación Exitosa");
        model.addAttribute("clase", "alert alert-success");
        return "loginCreate";
    }


}
