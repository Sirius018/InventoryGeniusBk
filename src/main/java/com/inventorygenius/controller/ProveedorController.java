package com.inventorygenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventorygenius.model.Proveedor;
import com.inventorygenius.repository.IPaisRepository;
import com.inventorygenius.repository.IProveedorRepository;
import com.inventorygenius.repository.ITipoRepository;

@Controller
public class ProveedorController {

	@Autowired
	private IProveedorRepository repoProveedor;

	@Autowired
	private ITipoRepository repoTipo;

	@Autowired
	private IPaisRepository repoPais;


	@GetMapping("/home/HomeAcount/listadoProveedores")
	public String listarProveedor(Model model) {
		model.addAttribute("listaProveedor", repoProveedor.findAll());
		model.addAttribute("lstTipo", repoTipo.findAll());
		model.addAttribute("lstPais", repoPais.findAll());
		model.addAttribute("proveedor", new Proveedor());
		return "Proveedor";
	}

	@PostMapping("/proveedor/guardar")
	public String guardarProveedor(@ModelAttribute Proveedor proveedor, Model model ) {
		model.addAttribute("listaProveedor", repoProveedor.findAll());
		model.addAttribute("lstTipo", repoTipo.findAll());
		model.addAttribute("lstPais", repoPais.findAll());
		return "Proveedor";
	}
}