package br.com.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.beans.Produto;
import br.com.teste.model.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> load (@PathVariable  Integer id) {
		Produto p = this.produtoService.findOne(id);
		HttpStatus status = p!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity(p , status);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> list() {
		List<Produto> produtos = this.produtoService.findAll();
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		Produto produtoNew = this.produtoService.save(produto);
		return new ResponseEntity(produtoNew , HttpStatus.CREATED );
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		produtoService.delete(id);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	

}
