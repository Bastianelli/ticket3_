package it.wish.ticket3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.wish.ticket3.model.Tecnico;
@Repository
public interface TecnicoRepository extends CrudRepository<Tecnico, Integer> {

}
