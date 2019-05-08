package br.com.teste.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.model.beans.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
