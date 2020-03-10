package it.wish.ticket3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.wish.ticket3.model.Componente;
import it.wish.ticket3.repository.ComponenteRepository;

@Service
public class ComponenteService {
	@Autowired
	private ComponenteRepository componenteRepository;

	public Iterable<Componente> findAll() {
		return this.componenteRepository.findAll();
	}
	public Optional<Componente> findById(Integer id) {
		return componenteRepository.findById(id);
	}
	
}
