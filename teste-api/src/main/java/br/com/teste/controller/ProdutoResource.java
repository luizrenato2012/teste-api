package br.com.teste.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.beans.Produto;
import br.com.teste.model.repository.ProdutoRepository;
import br.com.teste.model.repository.filtro.FiltroProduto;
import br.com.teste.model.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> load (@PathVariable  Integer id) {
		Produto p = this.produtoService.findOne(id);
		HttpStatus status = p!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity(p , status);
	}
	
	@GetMapping
	public Page <Produto> list(FiltroProduto filtroProduto, Pageable pageAble) {
		return this.produtoRepository.filtrar(filtroProduto, pageAble);
	}
	
//	@GetMapping
//	public ResponseEntity<List<Produto>> listByDescricao(@RequestParam("descricao") String descricao) {
//		List<Produto> produtos = this.produtoService.listByDescricao(descricao);
//		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
//	}
	
	@PostMapping
	public ResponseEntity<Produto> save(@Valid @RequestBody Produto produto) {
		Produto produtoNew = this.produtoService.save(produto);
		return new ResponseEntity(produtoNew , HttpStatus.CREATED );
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Produto> update(@PathVariable Long codigo, @Valid @RequestBody Produto produto) {
		Produto produtoNew = this.produtoService.save(produto);
		return new ResponseEntity(produtoNew , HttpStatus.CREATED );
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtoService.delete(id);
	}
	
		
 
}
