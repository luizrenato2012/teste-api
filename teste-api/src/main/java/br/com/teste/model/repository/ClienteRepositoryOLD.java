package br.com.teste.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import br.com.teste.model.beans.Cliente;
import br.com.teste.model.beans.ObjetoNaoEncontradoException;

@Repository
public class ClienteRepositoryOLD {
	
private Map<Integer,Cliente> mapClientes;
	
	@PostConstruct
	private void init () {
		mapClientes = new HashMap<>();
		mapClientes.put(1, new Cliente(1, "Maria Selma Vargas", "111.111.111-11"));
		mapClientes.put(2, new Cliente(2, "Carlos Santos Ferreira", "222.222.222-22"));
		mapClientes.put(3, new Cliente(3, "Cristia Farraes", "333.333.333-33"));
		mapClientes.put(4, new Cliente(4, "Malva Collins", "444.444.444-44"));
		mapClientes.put(5, new Cliente(5, "Shiltiso Talvares Rasni", "555.555.555-55"));
		mapClientes.put(6, new Cliente(6, "Helga Nunes Salvatore ", "666.666.666-66"));
		mapClientes.put(7, new Cliente(7, "Robertilnaldo Ferende", "777.777.777-77"));
	}
	
	public List<Cliente> listAll() {
		return new ArrayList<>(mapClientes.values());
	}
	
	public Cliente save(Cliente cliente) {
		if (cliente.getId()==null || cliente.getId()==0) {
			cliente.setId(this.mapClientes.size());
			this.mapClientes.put(cliente.getId(), cliente);
			return cliente;
		}
			
		Cliente old = this.mapClientes.get(cliente.getId());
		if (old==null) {
			throw new ObjetoNaoEncontradoException("Cliente id "+ cliente.getId() + " nao encontrado");
		}
		old.setNome(cliente.getNome());
		old.setTelefone(cliente.getTelefone());
		old.setCpf(cliente.getCpf());
		return old;
	}
	
	public void delete(Integer id) {
		Cliente cliente = this.mapClientes.get(id);
		if(cliente==null) {
			throw new ObjetoNaoEncontradoException("Cliente id "+ id + " nao encontrado");
		}
		this.mapClientes.remove(cliente);
	}
	
	public Cliente load (Integer id) {
		return this.mapClientes.get(id);
	}
	
	public List<Cliente> listByNome(String nome) {
		List<Cliente> lista = this.mapClientes.values()
				.stream()
				.filter( cliente -> cliente.getNome().toUpperCase().contains(nome.toUpperCase()))
				.collect(Collectors.toList());
		return lista;		
	}

}
