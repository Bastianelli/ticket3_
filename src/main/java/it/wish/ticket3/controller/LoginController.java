package it.wish.ticket3.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
	
	@RequestMapping("/errore")
	public String mostraErrore() {
		return "errore";
	}
	
	@RequestMapping("/")
	public String mostraHome(Authentication authentication) {
		boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities
         = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                System.out.println("sei un UTENTE");
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                System.out.println("sei un AMMINISTRATORE");
                break;
            }
        }
 
        if (isUser) {
            return "userIndex";
        } else if (isAdmin) {
            return "adminIndex";
        } else {
            throw new IllegalStateException();
        }
	}

}