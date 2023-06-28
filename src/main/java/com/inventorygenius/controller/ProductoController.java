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

import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;

import javax.sql.DataSource;

import org.springframework.core.io.ResourceLoader;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ProductoController {
	/*crear los objetos para el repositorio*/

	@Autowired
	private IProductoRepository repoProducto;

	@Autowired
	private IProveedorRepository repoProveedor;

	@Autowired
	private ICategoriaRepository repoCategoria;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ResourceLoader resourceLoader;


	@GetMapping("/producto/listadopdf")
	public void reporteProducto(HttpServletResponse response) {
		// descargar directamente en un archivo
		// response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");

		// el pdf se muestre en pantalla
		response.setHeader("Content-Disposition", "inline;");
		// tipo de contenido
		response.setContentType("application/pdf");
		try {
			// obtener el recurso a utilizar -> jasper
			String ru = resourceLoader.getResource("classpath:reportes/ReporteProductos.jasper").getURI().getPath();
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


	@PostMapping("/producto/guardar")
	public String guardarEmpresa(@ModelAttribute Producto producto, Model model) {
		model.addAttribute("boton","Registrar");
		try {
			repoProducto.save(producto);
			if (model.getAttribute("boton").equals("Actualizar")) {
				model.addAttribute("mensaje", "Operación Exitosa");
			} else if (model.getAttribute("boton").equals("Registrar")) {
				model.addAttribute("mensaje", "Operación Exitosa");
			}
			model.addAttribute("clase", "alert alert-success");
			model.addAttribute("boton", "Registrar");
			model.addAttribute("listaProducto", repoProducto.findAll());
			model.addAttribute("lstCategoria", repoCategoria.findAll());
			model.addAttribute("lstProveedor", repoProveedor.findAll());
		} catch (Exception e) {
			model.addAttribute("listaProducto", repoProducto.findAll());
			model.addAttribute("lstCategoria", repoCategoria.findAll());
			model.addAttribute("lstProveedor", repoProveedor.findAll());
			model.addAttribute("mensaje", "No se pudo registrar");
			model.addAttribute("clase", "alert alert-danger");
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
