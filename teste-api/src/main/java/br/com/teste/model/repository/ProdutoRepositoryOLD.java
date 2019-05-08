package br.com.teste.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import br.com.teste.model.beans.Produto;
import br.com.teste.model.beans.ObjetoNaoEncontradoException;

@Repository
public class ProdutoRepositoryOLD {

	private Map<Integer,Produto> mapProdutos;
	
	@PostConstruct
	private void init () {
		mapProdutos = new HashMap<>();
		mapProdutos.put(1, new Produto(1, "Mesa", 350));
		mapProdutos.put(2, new Produto(2, "Cadeira", 100));
		mapProdutos.put(3, new Produto(3, "Armario de cozinha", 2100));
		mapProdutos.put(4, new Produto(4, "Mesa de computador", 350));
		mapProdutos.put(5, new Produto(5, "Guarda roupas", 2500));
		mapProdutos.put(6, new Produto(6, "Cama de Solteiro", 500));
		mapProdutos.put(7, new Produto(7, "Cama de Casal", 2500));
	}
	
	public List<Produto> listAll() {
		return new ArrayList<>(mapProdutos.values());
	}
	
	public Produto save(Produto produto) {
		if (produto.getId()==null || produto.getId()==0) {
			produto.setId(this.mapProdutos.size());
			this.mapProdutos.put(produto.getId(), produto);
			return produto;
		}
			
		Produto old = this.mapProdutos.get(produto.getId());
		if (old==null) {
			throw new ObjetoNaoEncontradoException("Produto id "+ produto.getId() + " nao encontrado");
		}
		old.setDescricao(produto.getDescricao());
		old.setPreco(produto.getPreco());
		return old;
	}
	
	public void delete(Integer id) {
		Produto produto = this.mapProdutos.get(id);
		if(produto==null) {
			throw new ObjetoNaoEncontradoException("Produto id "+ id + " nao encontrado");
		}
		this.mapProdutos.remove(produto);
	}
	
	public Produto load (Integer id) {
		return this.mapProdutos.get(id);
	}
	
	public List<Produto> listByDescricao(String descricao) {
		List<Produto> lista = this.mapProdutos.values()
				.stream()
				.filter( produto -> produto.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
				.collect(Collectors.toList());
		return lista;		
	}
	
	
}
