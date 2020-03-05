package it.wish.ticket3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.wish.ticket3.model.Cliente;
import it.wish.ticket3.model.Tecnico;
import it.wish.ticket3.repository.ClienteRepository;
//TODO DA COMPLETARE I SERVIZZI
@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Iterable<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}
	public Optional<Cliente> findById(Integer id) {
		return clienteRepository.findById(id);
	}
	public Cliente findByDenominazione(String denominazione) {
		List<Cliente> clienti = (List<Cliente>) clienteRepository.findAll();
		if(clienti.isEmpty() == false) {
			for(Cliente cliente : clienti) {
				if(cliente.getDenominazione().equals(denominazione)) {
					return cliente;
				}
			}
		}
		return null;
	}
	

}
