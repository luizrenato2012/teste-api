package br.com.teste.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.api.model.beans.Cliente;
import br.com.teste.api.model.repository.filtro.ClienteRepositoryQuery;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, ClienteRepositoryQuery {
	
	@Query("select c from Cliente c join fetch c.pessoa p where p.nome like %?1%")
	public List<Cliente> findByNome(String nome);

}
