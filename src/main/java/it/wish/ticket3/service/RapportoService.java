package it.wish.ticket3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.wish.ticket3.model.Rapporto;
import it.wish.ticket3.repository.RapportoRepository;
@Service
public class RapportoService {
	@Autowired
	private RapportoRepository rapportoRepository;

	public Iterable<Rapporto> findAll() {
		return this.rapportoRepository.findAll();
	}
	public Optional<Rapporto> findById(Integer id) {
		return rapportoRepository.findById(id);
	}
}
