package com.inventorygenius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("boton","Registrar");

		return "Proveedor";
	}

	@PostMapping("/proveedor/guardar")
	public String guardarProveedor(@ModelAttribute Proveedor proveedor, Model model ) {
		try {
			repoProveedor.save(proveedor);
			model.addAttribute("mensaje","Actualización Exitosa");
			model.addAttribute("clase","alert alert-success");
			model.addAttribute("boton","Registrar");
			model.addAttribute("listaProveedor", repoProveedor.findAll());

			model.addAttribute("lstTipo", repoTipo.findAll());
			model.addAttribute("lstPais", repoPais.findAll());
		}
		catch (Exception e) {
			model.addAttribute("listaProveedor", repoProveedor.findAll());
			model.addAttribute("lstTipo", repoTipo.findAll());
			model.addAttribute("lstPais", repoPais.findAll());
			model.addAttribute("mensaje","No se pudo registrar");
			model.addAttribute("clase","alert alert-danger");
		}

		return "Proveedor";
	}


	@PostMapping("/buscarproveedor")
	public String buscarProveedor(@RequestParam (name= "cod_prov") int cod_prov, Model model) {
		System.out.println(cod_prov);
		model.addAttribute("proveedor", repoProveedor.findById(cod_prov));

		model.addAttribute("listaProveedor", repoProveedor.findAll());
		model.addAttribute("lstTipo", repoTipo.findAll());
		model.addAttribute("lstPais", repoPais.findAll());
		model.addAttribute("boton","Actualizar");
		return "Proveedor";
	}



}