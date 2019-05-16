package br.com.teste.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.api.model.beans.Produto;
import br.com.teste.api.model.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto save(Produto produto) {
		return this.repository.save(produto);
	}

	public void delete (Integer id) {
		this.repository.delete(id);
	}
	
	public Produto findOne(Integer id) {
		return this.repository.findOne(id);
	}
	
	public List<Produto> findAll() {
		return this.repository.findAll();
	}
	
	public List<Produto> listByDescricao(String descricao) {
		return this.repository.findByDescricaoContainingIgnoreCase(descricao);
	}

}
