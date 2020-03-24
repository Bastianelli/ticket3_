package it.wish.ticket3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.wish.ticket3.model.Cliente;
import it.wish.ticket3.model.Rapporto;
import it.wish.ticket3.repository.ClienteRepository;
import it.wish.ticket3.repository.RapportoRepository;
@Service
public class RapportoService {
	@Autowired
	private RapportoRepository rapportoRepository;
	@Autowired
	private ClienteService clienteService;
	
	public List<Rapporto> filtraRapporti(Integer idCliente, String anno, String mese){
		List<Rapporto> rapporti = new ArrayList<Rapporto>();
		List<Rapporto> rapportiTotali = new ArrayList<Rapporto>();
		List<Rapporto> rapportiCliente = new ArrayList<Rapporto>();
		rapportiTotali = (List<Rapporto>) rapportoRepository.findAll();
		Cliente cliente = new Cliente();
		cliente = clienteService.findById(idCliente).get();
		rapportiCliente = cliente.getRapporti();
		if (idCliente == 0) {
			if (!anno.equals("0") && !mese.equals("0")) {
				rapporti = this.findByAnno(rapportiTotali, anno);
				rapporti = this.findByMese(rapporti, mese);
			}else if(anno.equals("0") && !mese.equals("0")) {
				rapporti = this.findByMese(rapportiTotali, mese);
			}else if(!anno.equals("0") && mese.equals("0")) {
				rapporti = this.findByAnno(rapportiTotali, anno);
			}
			rapporti = rapportiTotali;
		}else {
			if (!anno.equals("0") && !mese.equals("0")) {
				rapporti = this.findByAnno(rapportiCliente, anno);
				rapporti = this.findByMese(rapporti, mese);
			}else if(anno.equals("0") && !mese.equals("0")) {
				rapporti = this.findByMese(rapportiCliente, mese);
			}else if(!anno.equals("0") && mese.equals("0")) {
				rapporti = this.findByAnno(rapportiCliente, anno);
			}
			rapporti = rapportiCliente;
		}
		return rapporti;
	}

	public Iterable<Rapporto> findAll() {
		return this.rapportoRepository.findAll();
	}
	
	public Optional<Rapporto> findById(Integer id) {
		return rapportoRepository.findById(id);
	}
	
	public List<Rapporto> findByAnno(String anno){
		List<Rapporto> rapportiTrovati = new ArrayList<>();
		List<Rapporto> rapportiTotali = new ArrayList<>();
		rapportiTotali = (List<Rapporto>) rapportoRepository.findAll();
		if (rapportiTotali.isEmpty()) {
			return rapportiTrovati;
		}else {
			for(Rapporto rapporto : rapportiTotali) {
				if (rapporto.getIntervento().getAnno().equals(anno)) 
					rapportiTrovati.add(rapporto);
			}
		}
		return rapportiTrovati;
	}
	
	public List<Rapporto> findByAnno(List<Rapporto> rapporti, String anno){
		List<Rapporto> rapportiTrovati = new ArrayList<>();
		if (rapporti.isEmpty()) {
			return rapportiTrovati;
		}else {
			for(Rapporto rapporto : rapporti) {
				if (rapporto.getIntervento().getAnno().equals(anno)) 
					rapportiTrovati.add(rapporto);
				
			}
		}
		return rapportiTrovati;
	}
	
	public List<Rapporto> findByMese(String mese){
		List<Rapporto> rapportiTrovati = new ArrayList<>();
		List<Rapporto> rapportiTotali = new ArrayList<>();
		rapportiTotali = (List<Rapporto>) rapportoRepository.findAll();
		if (rapportiTotali.isEmpty()) {
			return rapportiTrovati;
		}else {
			for(Rapporto rapporto : rapportiTotali) {
				if (rapporto.getIntervento().getMese().equals(mese)) 
					rapportiTrovati.add(rapporto);
			}
		}
		return rapportiTrovati;
	}
	
	public List<Rapporto> findByMese(List<Rapporto> rapporti, String mese){
		List<Rapporto> rapportiTrovati = new ArrayList<>();
		if (rapporti.isEmpty()) {
			return rapportiTrovati;
		}else {
			for(Rapporto rapporto : rapporti) {
				if (rapporto.getIntervento().getMese().equals(mese)) 
					rapportiTrovati.add(rapporto);
			}
		}
		return rapportiTrovati;
	}
}
