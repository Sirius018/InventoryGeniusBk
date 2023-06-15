package com.inventorygenius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	/* abrir este despues de logearte */
	@GetMapping("/home/config")
	public String configs() {
		return "Home_Acount";
	} 
	
	/* primera pagina que debe ejecutarse */
	@GetMapping("/home")
	public String home() {
		//return "Home_Acount";
		return "Index";
	}
	/* pagina de referencia: https://webflow.com/blog/business-website-examples */

}
