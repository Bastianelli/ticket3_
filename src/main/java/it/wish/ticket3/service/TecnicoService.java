package it.wish.ticket3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.wish.ticket3.model.Tecnico;
import it.wish.ticket3.repository.TecnicoRepository;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Iterable<Tecnico> findAll() {
		return this.tecnicoRepository.findAll();
	}
	public Optional<Tecnico> findById(Integer id) {
		return tecnicoRepository.findById(id);
	}

	public Tecnico findTecnico(String nome, String cognome) {
		Tecnico tecnico = new Tecnico();
		if (nome != "" && cognome != "") {
			tecnico = this.findByNomeCognome(nome, cognome);
			return tecnico;
		}else if (nome != "" && cognome == "") {
			tecnico = this.findByNome(nome);
			return tecnico;
		}else if (nome == "" && cognome != "") {
			tecnico = this.findByCognome(cognome);
			return tecnico;
		}else {
			return null;
		}
	}

	public Tecnico findByNome(String nome) {
		List<Tecnico> tecnici = (List<Tecnico>) tecnicoRepository.findAll();
		if(tecnici.isEmpty() == false) {
			for(Tecnico tecnico : tecnici) {
				if(tecnico.getNome().equals(nome)) {
					return tecnico;
				}
			}
		}
		return null;
	}

	public Tecnico findByCognome(String cognome) {
		List<Tecnico> tecnici = (List<Tecnico>) tecnicoRepository.findAll();
		if(tecnici.isEmpty() == false) {
			for(Tecnico tecnico : tecnici) {
				if(tecnico.getCognome().equals(cognome)) {
					return tecnico;
				}
			}
		}
		return null;
	}

	public Tecnico findByNomeCognome(String nome, String cognome) {
		List<Tecnico> tecnici = (List<Tecnico>) tecnicoRepository.findAll();
		if(tecnici.isEmpty() == false) {
			for(Tecnico tecnico : tecnici) {
				if(tecnico.getNome().equals(nome) && tecnico.getCognome().equals(cognome)) {
					return tecnico;
				}
			}
		}
		return null;
	}
}
