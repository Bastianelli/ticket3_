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
	
	//filtra i rapporti attingendo dai rapporti del cliente passato come argomento
	//come filtraRapporti(List<Rapporto> rapporti, String anno, String mese) ma prendendosi
	//la lista dei rapporti del Cliente passato come parametro
	public List<Rapporto> filtraRapporti(Cliente cliente, String anno, String mese){
		return this.filtraRapporti(cliente.getRapporti(), anno, mese);
	}
	
	//filtra i rapporti attingendo dalla lista di rapporti passata come argomento
	public List<Rapporto> filtraRapporti(List<Rapporto> rapporti, String anno, String mese){
		List<Rapporto> rapportiTrovati = new ArrayList<Rapporto>();
		if (anno.equals("0")) {
			if(mese.equals("0")) {
				//se entrambi sono 0 ritorna la lista rapporti invariata
				return rapporti;
			}else {
				//se anno==0 e mese!=0 ritorna solo i rapporti con il mese specificato
				return this.findByMese(rapporti, mese);
			}
		}else {
			//ramo se anno != 0
			if(mese.equals("0")) {
				//se anno!=0 e mese==0 ritorna solo i rapporti con l'anno specificato
				return this.findByAnno(rapporti, anno);
			}else {
				//se entrambi sono diversi da 0 provo a filtrare per anno
				rapportiTrovati = this.findByAnno(rapporti, anno);
				//se il filtraggio per anno non ha trovato alcun elemento ritorno 
				//i rapporti con il mese specificato presi dalla lista totale passata come argomento
				if (rapportiTrovati.isEmpty()) {
					return this.findByMese(rapporti, mese);
					//se il filtraggio per anno ha trovato almeno un elemento
					//ritrno i rapporti con il mese specificato presi dai rapporti 
					//già filtrati per anno
				}else {
					return this.findByMese(rapportiTrovati, mese);
				}
			}
			
		}
	}
	
	//filtra i risultati attingendo da tutti i rapporti
	//TODO togli Cliente, usa metodo su tutti i rapporti
	public List<Rapporto> filtraRapporti(String anno, String mese){
		return this.filtraRapporti((List<Rapporto>) rapportoRepository.findAll(), anno, mese);
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
