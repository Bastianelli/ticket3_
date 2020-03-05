package it.wish.ticket3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.wish.ticket3.model.Cliente;
import it.wish.ticket3.model.Rapporto;
import it.wish.ticket3.model.Tecnico;
import it.wish.ticket3.repository.RapportoRepository;
import it.wish.ticket3.service.ClienteService;
import it.wish.ticket3.service.RapportoService;
import it.wish.ticket3.service.TecnicoService;

@Controller
public class RapportoController {
	@Autowired
	private RapportoRepository rapportoRepository;
	@Autowired
	private RapportoService rapportoService;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	private String errore = "";
	
	@RequestMapping("/rapporto")
	public String mostraFormRapporto() {
		return "rapporto";
	}
	//TODO SPOSTARE RESPONSABILITA SU RAPPORTOSERVICE (ADDTECNICO, ADDCLIENTE)
	@PostMapping("/rapporto/add")
	public String aggiungiRapporto(@RequestParam(name="denominazione") String denominazione, 
			  @RequestParam(name="cognome") String cognome, Model model) {
		Rapporto rapporto = new Rapporto();
		Tecnico tecnico = tecnicoService.findByCognome(cognome);
		Cliente cliente = clienteService.findByDenominazione(denominazione);
		if (tecnico != null && cliente != null) {
			rapporto.setCliente(cliente);
			rapporto.setTecnico(tecnico);
			rapportoRepository.save(rapporto);
			model.addAttribute("rapporto", rapporto);
			return "rapportoInserito";
		}else {
			errore = "AGGIUNGI RAPPORTO";
			model.addAttribute("messaggioErrore", errore);
			return "errore";
		}
	}
}
