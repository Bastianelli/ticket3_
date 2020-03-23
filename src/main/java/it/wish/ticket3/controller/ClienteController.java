package it.wish.ticket3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.wish.ticket3.model.Cliente;
import it.wish.ticket3.service.ClienteService;
import it.wish.ticket3.model.Tecnico;
import it.wish.ticket3.repository.ClienteRepository;

@Controller
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping("/cliente")
	public String mostraFormCliente() {
		return "cliente";
	}
	
	  @PostMapping("/cliente/add")
	  public String showLoginPage(@RequestParam(name="denominazione", required=false, defaultValue="nome società") String denominazione, 
			  @RequestParam(name="indirizzo", required=false, defaultValue="via di...") String indirizzo, 
			  @RequestParam(name="localita", required=false, defaultValue="") String localita, 
			  @RequestParam(name="cap", required=false, defaultValue="00123") String cap,
			  @RequestParam(name="citta", required=false, defaultValue="") String citta,
			  @RequestParam(name="partitaIva", required=false, defaultValue="") String partitaIva, Model model) {
		  Cliente cliente = new Cliente();
		  cliente.setDenominazione(denominazione);
		  cliente.setIndirizzo(indirizzo);
		  cliente.setLocalita(localita);
		  cliente.setCap(cap);
		  cliente.setCitta(citta);
		  cliente.setPartitaIva(partitaIva);
		  clienteRepository.save(cliente);
		  model.addAttribute("denominazione", denominazione);
		  model.addAttribute("indirizzo", indirizzo);
		  model.addAttribute("localita", localita);
		  model.addAttribute("cap", cap);
		  model.addAttribute("citta", citta);
		  model.addAttribute("partitaIva", partitaIva);
		  System.out.println("STAMPA PROVENIENTE DAL CONTROLLER CLIENTE");
	    return "clienteInserito";
	  }
	  
	  //TODO
	  @GetMapping("/cliente/find")
	  public String trovaCliente(@RequestParam(name="denominazione") String denominazione, Model model) {
		  Cliente cliente = new Cliente();
		  cliente = clienteService.findByDenominazione(denominazione);
		  model.addAttribute("cliente", cliente);
	    return "schedaCliente";
	  }
	  
	  @GetMapping("/cliente/all")
	  public String mostraTuttiClienti(Model model) {
		    // This returns a JSON or XML with the users
		  model.addAttribute("clienti", clienteRepository.findAll());
		    return "mostraClienti";
	  }
}
