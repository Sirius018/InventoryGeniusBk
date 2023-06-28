package com.inventorygenius.controller;

import java.io.OutputStream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventorygenius.model.Empresa;
import com.inventorygenius.repository.IEmpresaRepository;
import com.inventorygenius.repository.IPaisRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class EmpresaController {
    @Autowired
    private IEmpresaRepository repoEmpresa;

    @Autowired
    private IPaisRepository repoPais;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping("/empresa/listadopdf")
    public void reporteProducto(HttpServletResponse response) {
        // descargar directamente en un archivo
        // response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");

        // el pdf se muestre en pantalla
        response.setHeader("Content-Disposition", "inline;");
        // tipo de contenido
        response.setContentType("application/pdf");
        try {
            // obtener el recurso a utilizar -> jasper
            String ru = resourceLoader.getResource("classpath:reportes/ReporteEmpresa.jasper").getURI().getPath();
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


    @PostMapping("/empresa/guardar")
    public String guardarEmpresa(@ModelAttribute Empresa empresa, Model model ) {
        model.addAttribute("boton","Registrar");
        try {
            repoEmpresa.save(empresa);
            model.addAttribute("mensaje","Operación Exitosa");
            model.addAttribute("clase","alert alert-success");
            model.addAttribute("boton","Registrar");
            model.addAttribute("listaEmpresa", repoEmpresa.findAll());
            model.addAttribute("lstPais", repoPais.findAll());
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("listaEmpresa", repoEmpresa.findAll());
            model.addAttribute("lstPais", repoPais.findAll());
            model.addAttribute("mensaje","No se pudo registrar");
            model.addAttribute("clase","alert alert-danger");
        }
        return "Empresa";
    }



    @PostMapping("/buscarempresa")
    public String buscarProveedor(@RequestParam (name= "id_emp") int id_emp, Model model) {
        System.out.println(id_emp);
        model.addAttribute("empresa", repoEmpresa.findById(id_emp));
        model.addAttribute("listaEmpresa", repoEmpresa.findAll());
        model.addAttribute("lstPais", repoPais.findAll());

        model.addAttribute("boton","Actualizar");
        return "Empresa";
    }


    @GetMapping("/home/HomeAcount/listadoEmpresa")
    public String listaEmp(Model model) {
        model.addAttribute("listaEmpresa", repoEmpresa.findAll());
        model.addAttribute("lstPais", repoPais.findAll());

        model.addAttribute("empresa", new Empresa());
        model.addAttribute("boton","Registrar");

        return "Empresa";
    }
}
