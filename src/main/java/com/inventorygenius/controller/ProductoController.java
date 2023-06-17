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
	/*
	@Autowired
	private ICategoriaRepository repoCategoria;*/
	
	/*
	 @GetMapping("/formulario")
	    public String mostrarFormulario(Model model) {
	        Iterable<Categoria> categorias = repoCategoria.findAll();
	        model.addAttribute("LstCategorias", categorias);
	        return "Productos";
	    }

	 */
	 
	@GetMapping("/listado")
	public String listarProductos(Model model) {
		model.addAttribute("listaProducto", repoProducto.findAll());
		return "Productos";
	}
	

}
