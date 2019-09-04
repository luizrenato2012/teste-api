package br.com.teste.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.api.model.beans.Produto;
import br.com.teste.api.model.repository.ProdutoRepository;
import br.com.teste.api.model.repository.filtro.FiltroProduto;
import br.com.teste.api.model.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Produto> load (@PathVariable  Integer id) {
		Produto p = this.produtoService.findOne(id);
		HttpStatus status = p!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Produto>(p , status);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
	public Page <Produto> list(FiltroProduto filtroProduto, Pageable pageAble) {
		return this.produtoRepository.filtrar(filtroProduto, pageAble);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Produto> save(@Valid @RequestBody Produto produto) {
		Produto produtoNew = this.produtoService.save(produto);
		return new ResponseEntity<Produto>(produtoNew , HttpStatus.CREATED );
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Produto> update(@PathVariable Long codigo, @Valid @RequestBody Produto produto) {
		Produto produtoNew = this.produtoService.save(produto);
		return new ResponseEntity<Produto>(produtoNew , HttpStatus.CREATED );
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_EXCLUIR_PRODUTO') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtoService.delete(id);
	}
 
}
