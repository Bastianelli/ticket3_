package it.wish.ticket3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.wish.ticket3.model.Intervento;
import it.wish.ticket3.model.Rapporto;
import it.wish.ticket3.repository.InterventoRepository;
import it.wish.ticket3.repository.RapportoRepository;
import it.wish.ticket3.service.InterventoService;
import it.wish.ticket3.service.RapportoService;

@Controller
public class InterventoController {
	@Autowired
	private InterventoRepository interventoRepository;
	@Autowired
	private InterventoService interventoService;
	@Autowired
	private RapportoService rapportoService;
	@Autowired
	private RapportoRepository rapportoRepository;
	
	@RequestMapping("/intervento")
	public String mostraIntervento() {
		return "intervento";
	}
	
	@RequestMapping("/interventoToRapporto")
	public String mostraInterventoToRapporto(@RequestParam(name="idRapporto") String idRapporto, Model model) {
		Rapporto rapporto= new Rapporto();
		rapporto = rapportoService.findById(Integer.parseInt(idRapporto)).get();
		model.addAttribute("rapporto", rapporto );
		return "interventoToRapporto";
	}
	
	@RequestMapping("/intervento/add")
	public String aggiungiIntervento(@RequestParam(name = "data") String data, 
			@RequestParam(name = "inizio") String inizio,
			@RequestParam(name = "fine") String fine,
			@RequestParam(name = "viaggio") String viaggio,
			@RequestParam(name = "descrizioneIntervento") String descrizioneIntervento,
			 Model model) {
		Intervento intervento = new Intervento();
		intervento.setData(data);
		intervento.setInizio(inizio);
		intervento.setFine(fine);
		intervento.setViaggio(viaggio);
		intervento.setDescrizioneIntervento(descrizioneIntervento);
		intervento.setTempoTotale();
		interventoRepository.save(intervento);
		System.out.println(intervento.toString());
		return"intervento";
	}
	
	@RequestMapping("/intervento/addToRapporto")
	public String aggiungiInterventoToRapporto(@RequestParam(name = "data") String data, 
			@RequestParam(name = "inizio") String inizio,
			@RequestParam(name = "fine") String fine,
			@RequestParam(name = "viaggio") String viaggio,
			@RequestParam(name = "descrizioneIntervento") String descrizioneIntervento,
			@RequestParam(name="idRapporto") String idRapporto,
			 Model model) {
		Intervento intervento = new Intervento();
		Rapporto rapporto= new Rapporto();
		rapporto = rapportoService.findById(Integer.parseInt(idRapporto)).get();
		intervento.setData(data);
		intervento.setInizio(inizio);
		intervento.setFine(fine);
		intervento.setViaggio(viaggio);
		intervento.setDescrizioneIntervento(descrizioneIntervento);
		intervento.setTempoTotale();
		intervento.setRapporto(rapporto);
		rapporto.setIntervento(intervento);
		
		interventoRepository.save(intervento);
		rapportoRepository.save(rapporto);
		model.addAttribute("rapporto", rapporto);
		System.out.println(intervento.toString());
		return "componenteToRapporto";
	}
}
