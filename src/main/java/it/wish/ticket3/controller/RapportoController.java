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
	
	
	
	@RequestMapping("/admin/rapporto")
	public String mostraFormRapporto(Model model) {
		List<Cliente> clienti = new ArrayList<>();
		clienti = (List<Cliente>) clienteService.findAll();
		List<Tecnico> tecnici = new ArrayList<>();
		tecnici = (List<Tecnico>) tecnicoService.findAll();
		model.addAttribute("clienti", clienti);
		model.addAttribute("tecnici", tecnici);
		return "rapporto";
	}
	
	@RequestMapping("/rapportoInserito")
	public String mostraRapportoInserito(@RequestParam(name="idRapporto") String idRapporto, Model model) {
		Rapporto rapporto= new Rapporto();
		rapporto = rapportoService.findById(Integer.parseInt(idRapporto)).get();
		model.addAttribute("rapporto", rapporto );
		return "rapportoInserito";
	}
	
	//TODO SPOSTARE RESPONSABILITA SU RAPPORTOSERVICE (ADDTECNICO, ADDCLIENTE)
	@PostMapping("/admin/rapporto/add")
	public String aggiungiRapporto(@RequestParam(name="denominazione") String denominazione, 
			@RequestParam(name="cognome") String cognome, @RequestParam(name="note") String note, Model model) {
		Rapporto rapporto = new Rapporto();
		Tecnico tecnico = tecnicoService.findByCognome(cognome);
		Cliente cliente = clienteService.findByDenominazione(denominazione);
		
			rapporto.setCliente(cliente);
			rapporto.setTecnico(tecnico);
			rapporto.setNote(note);
			cliente.addRapporto(rapporto);
			rapportoRepository.save(rapporto);
			model.addAttribute("rapporto", rapporto);
			System.out.println("/user/rapporto/add");
			return "interventoToRapporto";
		
	}
	
	//cerca e restituisce i rapporti che rispettano i criteri dati dai filtri
	@PostMapping("/rapporto/find")
	public String trovaRapportiCliente(@RequestParam(name="idCliente") String idCliente,
			@RequestParam(name="anno") String anno,
			@RequestParam(name="mese") String mese,
			/*
			 * @RequestParam(name="costoMax") String costoMax,
			 * 
			 * @RequestParam(name="costoMin") String costoMin,
			 */
			Model model) {
		Cliente cliente = new Cliente();
		cliente = clienteService.findById(Integer.parseInt(idCliente)).get();
		List<Cliente> clienti = new ArrayList<>();
		clienti = (List<Cliente>) clienteService.findAll();
//		List<Tecnico> tecnici = new ArrayList<>();
//		tecnici = (List<Tecnico>) tecnicoService.findAll();
		List<Rapporto> rapporti = new ArrayList<>();
		rapporti = rapportoService.filtraRapporti(cliente, anno, mese);
		System.out.println("id cliente = " + idCliente + "anno = " + anno + "mese = " + mese);
		System.out.println(anno);
		
		model.addAttribute("anno", anno);
		model.addAttribute("mese", mese);
		model.addAttribute("clienti", clienti);
//		model.addAttribute("tecnici", tecnici);
		model.addAttribute("cliente", cliente);
		model.addAttribute("rapporti", rapporti);
		return "mostraRapporti";
	}
}
