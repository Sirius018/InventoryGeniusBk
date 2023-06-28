package com.inventorygenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventorygenius.model.Empresa;
import com.inventorygenius.repository.IEmpresaRepository;
import com.inventorygenius.repository.IPaisRepository;

@Controller
public class EmpresaController {
    @Autowired
    private IEmpresaRepository repoEmpresa;

    @Autowired
    private IPaisRepository repoPais;



    @PostMapping("/empresa/guardar")
    public String guardarEmpresa(@ModelAttribute Empresa empresa, Model model ) {
        try {
            repoEmpresa.save(empresa);
            model.addAttribute("mensaje","Operación Exitosa");
            model.addAttribute("clase","alert alert-success");
            model.addAttribute("boton","Registrar");
            model.addAttribute("listaEmpresa", repoEmpresa.findAll());
            model.addAttribute("lstPais", repoPais.findAll());
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("listaEmpresa", repoEmpresa.findAll());
            model.addAttribute("lstPais", repoPais.findAll());
            model.addAttribute("mensaje","No se pudo registrar");
            model.addAttribute("clase","alert alert-danger");
        }
        return "Empresa";
    }



    @PostMapping("/buscarempresa")
    public String buscarProveedor(@RequestParam (name= "id_emp") int id_emp, Model model) {
        System.out.println(id_emp);
        model.addAttribute("empresa", repoEmpresa.findById(id_emp));
        model.addAttribute("listaEmpresa", repoEmpresa.findAll());
        model.addAttribute("lstPais", repoPais.findAll());

        model.addAttribute("boton","Actualizar");
        return "Empresa";
    }


    @GetMapping("/home/HomeAcount/listadoEmpresa")
    public String listaEmp(Model model) {
        model.addAttribute("listaEmpresa", repoEmpresa.findAll());
        model.addAttribute("lstPais", repoPais.findAll());

        model.addAttribute("empresa", new Empresa());
        model.addAttribute("boton","Registrar");

        return "Empresa";
    }
}
