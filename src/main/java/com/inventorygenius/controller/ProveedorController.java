package com.inventorygenius.controller;

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

import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ProveedorController {

	@Autowired
	private IProveedorRepository repoProveedor;

	@Autowired
	private ITipoRepository repoTipo;

	@Autowired
	private IPaisRepository repoPais;

	@Autowired
	private DataSource dataSource; 
	
	@Autowired
	private ResourceLoader resourceLoader; 
	
	
	@GetMapping("/proveedor/listadopdf")
	public void reporteProveedor(HttpServletResponse response) {
		// descargar directamente en un archivo
		// response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		
		// el pdf se muestre en pantalla
		response.setHeader("Content-Disposition", "inline;"); 
		// tipo de contenido
		response.setContentType("application/pdf");
		try {
			// obtener el recurso a utilizar -> jasper
			String ru = resourceLoader.getResource("classpath:reportes/ReporteProveedor.jasper").getURI().getPath();
			// combina el jasper + data / Ojo!!! null -> la conexión no tiene parámetros
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			// genera un archivo temporal
			OutputStream outStream = response.getOutputStream();
			// muestra el archivo
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	

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
			model.addAttribute("mensaje","Operación Exitosa");
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