package br.com.teste.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.model.beans.ObjetoNaoEncontradoException;
import br.com.teste.model.beans.Produto;
import br.com.teste.model.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto save(Produto produto) {
		if (produto.getId()!=null) {
			this.validaProduto(produto.getId());
		}
		return this.repository.save(produto);
	}

	private void validaProduto(Integer id) {
		if (this.repository.findOne(id)==null) {
			throw new ObjetoNaoEncontradoException("Produto id ["+ id + "] nao encontrado");
		}
	}
	
	public void delete (Integer id) {
		this.validaProduto(id);
		
		this.repository.delete(id);
	}
	
	public Produto findOne(Integer id) {
		return this.repository.findOne(id);
	}
	
	public List<Produto> findAll() {
		return this.repository.findAll();
	}

}
