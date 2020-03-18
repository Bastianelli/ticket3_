package it.wish.ticket3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	// Login form
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// Login form with error
	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	@RequestMapping("/primaPagina")
	public String showPrima(Model model){
		return("primaPagina");
	}
	
	@RequestMapping("/user")
	public String mostraUserIndex() {
		return "userIndex";
	}
	
	@RequestMapping("/admin")
	public String mostraAdminIndex() {
		return "adminIndex";
	}

}