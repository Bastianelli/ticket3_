package it.wish.ticket3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.wish.ticket3.model.Cliente;
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}
