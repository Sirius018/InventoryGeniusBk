package com.inventorygenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventorygenius.repository.IEmpresaRepository;

@Controller
public class EmpresaController {
	@Autowired
    private IEmpresaRepository repoEmpresa;


    @GetMapping("/listarEmpresa")
    public String listaEmp(Model model) {
        model.addAttribute("listaEmpresa", repoEmpresa.findAll());
        return "Empresa";
    }

}
