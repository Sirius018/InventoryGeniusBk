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
	private IProveedorRepository repoProveedor;
	
	


	
	
	@GetMapping("/home/HomeAcount/listadoProveedores")
	public String listarProveedor(Model model) {
		model.addAttribute("listaProveedor", repoProveedor.findAll());
		return "Proveedor";
	}
}