package com.inventorygenius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	
	@GetMapping("/home/HomeAcount")
	public String navbarAcount() {
		return "Home_Acount";
	} 
	
	
	@GetMapping("/home/NewAcount")
	public String newAcount() {
		return "LoginCreate";
	} 
	
	
	/* abrir este despues de logearte */
	@GetMapping("/home/Acount")
	public String signAcount() {
		return "LoginSign";
	} 
	
	/* primera pagina que debe ejecutarse */
	@GetMapping("/home")
	public String home() {
		//return "Home_Acount";
		return "Index";
	}
	/* pagina de referencia: https://webflow.com/blog/business-website-examples */

}
