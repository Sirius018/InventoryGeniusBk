package com.inventorygenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventorygenius.repository.IProductoRepository;

@Controller
public class ProductoController {
	/*crear los objetos para el repositorio*/
	@Autowired
	private IProductoRepository repoProducto;
	
	
	
	@GetMapping("/listado")
	public String listarProductos(Model model) {
		model.addAttribute("listaProducto", repoProducto.findAll());
		return "Productos";
	}
	

}
