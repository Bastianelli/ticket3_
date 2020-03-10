package it.wish.ticket3.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.wish.ticket3.model.Tecnico;
import it.wish.ticket3.repository.TecnicoRepository;
import it.wish.ticket3.service.TecnicoService;

@Controller
//@RequestMapping(path="/tecnico")
public class TecnicoController {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	
	@RequestMapping("/tecnico")
	public String mostraFormTecnico() {
		return "tecnico";
	}
	
	//TODO CAMBIA TECNICOREPOSITORY IN TECNICOSERVICE
	  @PostMapping("/tecnico/add")
	  public String aggiungiTecnico(@RequestParam(name="nome") String nome, 
			  @RequestParam(name="cognome") String cognome, Model model) {
		  Tecnico tecnico = new Tecnico();
		  tecnico.setNome(nome);
		  tecnico.setCognome(cognome);
		  tecnicoRepository.save(tecnico);
		  model.addAttribute("nome", nome);
		  model.addAttribute("cognome" ,cognome);
		  System.out.println("STAMPA PROVENIENTE DAL CONTROLLER TECNICO ADD");
	    return "tecnicoInserito";
	  }
	  
	  
	  
	  @GetMapping("/tecnico/find")
	  public String trovaTecnico(@RequestParam(name="nome") String nome, 
			  @RequestParam(name="cognome") String cognome, Model model) {
		  Tecnico tecnico = new Tecnico();
		  List<Tecnico> tecnici= new ArrayList<Tecnico>();
		   tecnico = tecnicoService.findTecnico(nome, cognome);
		   if(tecnico != null) {
			   tecnici.add(tecnico);
		   }		  
		  model.addAttribute("tecnici", tecnici);
		  System.out.println("STAMPA PROVENIENTE DAL CONTROLLER TECNICO FIND");
	    return "mostraTecnici";
	  }
	
	  @GetMapping("/tecnico/all")
	  public String mostraTuttiTecnici(Model model) {
		    // This returns a JSON or XML with the users
		  model.addAttribute("tecnici", tecnicoRepository.findAll());
		    return "mostraTecnici";
	  }
	  
	  
//	  @GetMapping("/all")
//	  public String showLoginPage(@RequestParam(name="nome", required=false, defaultValue="World") String nome, 
//			  @RequestParam(name="cognome", required=false, defaultValue="World2") String cognome, Model model) {
//		  model.addAttribute("nome", nome);
//		  model.addAttribute("cognome", cognome);
//		  System.out.println("STAMPA PROVENIENTE DAL CONTROLLER TECNICO");
//	    return "tecnico";
//	  }
}
