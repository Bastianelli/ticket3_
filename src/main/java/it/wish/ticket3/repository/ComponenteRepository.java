package it.wish.ticket3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.wish.ticket3.model.Componente;
@Repository
public interface ComponenteRepository extends CrudRepository<Componente, Integer> {

}
