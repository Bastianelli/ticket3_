package it.wish.ticket3.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.wish.ticket3.model.Componente;
import it.wish.ticket3.model.Rapporto;
import it.wish.ticket3.repository.ComponenteRepository;
import it.wish.ticket3.repository.RapportoRepository;
import it.wish.ticket3.service.ComponenteService;
import it.wish.ticket3.service.RapportoService;

@Controller
public class ComponenteController {
	@Autowired
	private ComponenteRepository componenteRepository;
	@Autowired
	private ComponenteService componenteService;
	@Autowired
	private RapportoService rapportoService;
	@Autowired
	private RapportoRepository rapportoRepository;
	//private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	@RequestMapping("/componente")
	public String mostraFormComponente(Model model) {

		  model.addAttribute("componenti", componenteService.findAll());
		  System.out.println("STAMPA PROVENIENTE DAL CONTROLLER COMPONENTE");
		return "componente";
	}
	
	@PostMapping("/componente/add")
	public String aggiungiComponente(@RequestParam(name="descrizione") String descrizione, 
			@RequestParam(name="costoUnitario") String costoUnitario,
			@RequestParam(name="quantita") String quantita, Model model) {
		
		Componente componente = new Componente();
		List<Componente> componenti = new ArrayList<>();
		componente.setDescrizione(descrizione);
		componente.setCostoUnitario(Double.parseDouble(costoUnitario));
		componente.setQuantita(Integer.parseInt(quantita));
		componente.calcolaCostoTotale();
		componenteRepository.save(componente);
		model.addAttribute("componente", componente);
		componenti = (List<Componente>)componenteService.findAll();
			model.addAttribute("componenti", componenti);
		
		System.out.println("STAMPA PROVENIENTE DAL CONTROLLER COMPONENTE ADD");
		
		return "componente";
	}
	
	@PostMapping("/componente/addToRapporto")
	public String aggiungiComponenteToRapporto(@RequestParam(name="descrizione") String descrizione, 
			@RequestParam(name="costoUnitario") String costoUnitario,
			@RequestParam(name="quantita") String quantita,
			@RequestParam(name="idRapporto") String idRapporto, Model model) {
		Componente componente = new Componente();
		List<Componente> componenti = new ArrayList<>();
		System.out.println("ID RAPPORTO" + idRapporto);
		Optional<Rapporto> rapportoOptional = rapportoService.findById(Integer.parseInt(idRapporto));
		Rapporto rapporto = rapportoOptional.get();
		componente.setDescrizione(descrizione);
		componente.setCostoUnitario(Double.parseDouble(costoUnitario));
		componente.setQuantita(Integer.parseInt(quantita));
		componente.calcolaCostoTotale();
		componenteRepository.save(componente);
		rapporto.addComponente(componente);
		rapportoRepository.save(rapporto);
		model.addAttribute("componente", componente);
		model.addAttribute("rapporto", rapportoOptional.get());
		componenti = (List<Componente>)componenteService.findAll();
		model.addAttribute("componenti", componenti);

		System.out.println(rapporto.toString());
		System.out.println("STAMPA PROVENIENTE DAL CONTROLLER COMPONENTE ADD TO RAPPORTO");
		
		return "rapportoInserito";
	}
	
	@GetMapping("/componente/all")
	  public String mostraComponenti(Model model) {
		    // This returns a JSON or XML with the users		  
		  model.addAttribute("componenti", componenteService.findAll());
		  System.out.println("STAMPA PROVENIENTE DAL CONTROLLER COMPONENTE ALL");
		    return "componente";
	  }


}
