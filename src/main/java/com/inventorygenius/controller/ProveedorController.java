package com.inventorygenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventorygenius.model.Proveedor;
import com.inventorygenius.repository.IProveedorRepository;

@Controller
public class ProveedorController {

	@Autowired
	private IProveedorRepository repoProv;
	
	
	/*
	@PostMapping("/proveedor/guardar")
	public String grabarCrudProducto(@ModelAttribute Proveedor proveedor, Model model) {
		System.out.println(proveedor);
		try {
			model.addAttribute("LstProductos", repoProv.findAll());
			repoProv.save(proveedor);
			model.addAttribute("mensaje","Registro OK");
			System.out.println(proveedor);
			
		} catch (Exception e) {
			model.addAttribute("mensaje","Error al Registrar");
		}
		model.addAttribute("btnRegistrarProveedor", "Registrar");
		
		return "Proveedor";
	}*/
	
	
	@GetMapping("/home/HomeAcount/listadoProveedores")
	public String listarProveedor(Model model) {
		model.addAttribute("listaProveedor", repoProv.findAll());
		return "Proveedor";
	}
}