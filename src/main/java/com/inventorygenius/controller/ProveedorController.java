package com.inventorygenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventorygenius.repository.IProveedorRepository;

@Controller
public class ProveedorController {

	@Autowired
	private IProveedorRepository repoProv;
	
	@GetMapping("/listadoProveedores")
	public String listarProveedor(Model model) {
		model.addAttribute("listaProveedor", repoProv.findAll());
		return "Proveedor";
	}
}