package com.inventorygenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.inventorygenius.model.Producto;
import com.inventorygenius.repository.ICategoriaRepository;
import com.inventorygenius.repository.IProductoRepository;
import com.inventorygenius.repository.IProveedorRepository;

@Controller
public class ProductoController {
	/*crear los objetos para el repositorio*/

	@Autowired
	private IProductoRepository repoProducto;

	@Autowired
	private IProveedorRepository repoProveedor;

	@Autowired
	private ICategoriaRepository repoCategoria;



	@PostMapping("/producto/guardar")
	public String guardarEmpresa(@ModelAttribute Producto producto, Model model ) {
		try {
			repoProducto.save(producto);
			model.addAttribute("mensaje","Operación Exitosa");
			model.addAttribute("clase","alert alert-success");
			model.addAttribute("boton","Registrar");
			model.addAttribute("listaProducto", repoProducto.findAll());
			model.addAttribute("lstCategoria", repoCategoria.findAll());
			model.addAttribute("lstProveedor", repoProveedor.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("listaProducto", repoProducto.findAll());
			model.addAttribute("lstCategoria", repoCategoria.findAll());
			model.addAttribute("lstProveedor", repoProveedor.findAll());
			model.addAttribute("mensaje","No se pudo registrar");
			model.addAttribute("clase","alert alert-danger");
		}
		return "Productos";
	}



	@PostMapping("/buscarproducto")
	public String buscarProveedor(@RequestParam (name= "cod_unico_prod") String cod_unico_prod, Model model) {
		/*System.out.println(cod_unico_prod);*/
		model.addAttribute("producto", repoProducto.findById(cod_unico_prod));
		model.addAttribute("listaProducto", repoProducto.findAll());
		model.addAttribute("lstCategoria", repoCategoria.findAll());
		model.addAttribute("lstProveedor", repoProveedor.findAll());

		model.addAttribute("boton","Actualizar");
		return "Productos";
	}



	@GetMapping("/home/HomeAcount/listadoProducto")
	public String listarProductos(Model model) {
		model.addAttribute("listaProducto", repoProducto.findAll());
		model.addAttribute("lstCategoria", repoCategoria.findAll());
		model.addAttribute("lstProveedor", repoProveedor.findAll());

		model.addAttribute("producto", new Producto());
		model.addAttribute("boton","Registrar");

		return "Productos";
	}
}
