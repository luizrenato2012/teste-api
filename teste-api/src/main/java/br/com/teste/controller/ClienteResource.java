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

import br.com.teste.model.beans.Cliente;
import br.com.teste.model.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping
	public ResponseEntity<List<Cliente>> listall() {
		return new ResponseEntity(this.clienteService.findAll(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> load(@PathVariable Integer id) {
		Cliente cliente = this.clienteService.findOne(id);
		return new ResponseEntity(cliente, cliente!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> findByNome(@PathVariable String nome) {
		List<Cliente> clientes = this.clienteService.findByNome(nome);
		boolean possuiClientes = clientes!=null && clientes.size()>0;
		return new ResponseEntity(clientes , possuiClientes ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente clienteNew = this.clienteService.save(cliente);
		boolean isInclusao = cliente.getId()==null || cliente.getId()==0;
		return new ResponseEntity( clienteNew, isInclusao ? HttpStatus.CREATED : HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		this.clienteService.delete(id);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}

}
