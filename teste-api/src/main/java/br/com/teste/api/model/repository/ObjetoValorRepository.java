package br.com.teste.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.teste.api.model.beans.ObjetoValor;

@Repository
public interface ObjetoValorRepository extends JpaRepository<ObjetoValor, Long>{
	
	@Query("select o from ObjetoValor o where o.codigo = :codigo")
	public ObjetoValor findByCodigo(@Param("codigo") String codigo);
	
	public List<ObjetoValor> findByTipo(String tipo);

}
