package br.com.teste.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.teste.model.beans.Produto;
import br.com.teste.model.repository.query.ProdutoRepositoryQuery;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoRepositoryQuery{
	
//	@Query("select p from Produto p where lower(p.descricao) like lower(%:descricao%)")
	public List<Produto> findByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

}
