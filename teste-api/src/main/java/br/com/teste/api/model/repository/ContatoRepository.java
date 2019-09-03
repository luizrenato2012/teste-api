package br.com.teste.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.api.model.beans.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
