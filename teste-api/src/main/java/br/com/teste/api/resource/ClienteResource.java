package br.com.teste.api.resource;

import java.util.List;

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

import br.com.teste.api.model.beans.Cliente;
import br.com.teste.api.model.repository.ClienteRepository;
import br.com.teste.api.model.repository.filtro.ClienteFilter;
import br.com.teste.api.model.repository.filtro.ResumoCliente;
import br.com.teste.api.model.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> load(@PathVariable Integer id) {
		Cliente cliente = this.clienteService.findOne(id);
		return new ResponseEntity(cliente, cliente!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
//	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')") TODO aplicar controle de permissoes
	public Page<Cliente> pesquisar(ClienteFilter ClienteFilter, Pageable pageable) { 
		return this.clienteRepository.filtrar(ClienteFilter, pageable);
	}
	
	@GetMapping(params="resumo")
//	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')") TODO aplicar controle de permissoes
	public Page<ResumoCliente> resumir(ClienteFilter ClienteFilter, Pageable pageable) { 
		return this.clienteRepository.resumir(ClienteFilter, pageable);
	}
	
	
	@PutMapping("/{codigo}") //TODO aplicar controle de permissoes
	public ResponseEntity<Cliente> update(@PathVariable Long codigo, @RequestBody Cliente cliente) {
		Cliente clienteSalvo = this.clienteService.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
	
	@PostMapping	//TODO aplicar controle de permissoes
	public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente) {
		Cliente clienteSalvo = this.clienteService.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@DeleteMapping("/{id}") //TODO aplicar controle de permissoes
	public ResponseEntity delete(@PathVariable Integer id) {
		this.clienteService.delete(id);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
//	@GetMapping("/teste")	//TODO aplicar controle de permissoes
	@ResponseStatus(HttpStatus.OK)
	public void teste() {
		this.clienteService.executaTeste();
	}

}
