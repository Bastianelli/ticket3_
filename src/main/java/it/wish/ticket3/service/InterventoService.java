package it.wish.ticket3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.wish.ticket3.model.Intervento;
import it.wish.ticket3.repository.InterventoRepository;

@Service
public class InterventoService {
	@Autowired
	private InterventoRepository interventoRepository;
	
	public Iterable<Intervento> findAll() {
		return this.interventoRepository.findAll();
	}
	public Optional<Intervento> findById(Integer id) {
		return interventoRepository.findById(id);
	}

}
