package br.com.teste.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.model.beans.Cliente;
import br.com.teste.model.beans.Endereco;
import br.com.teste.model.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findOne(Integer id) {
		return clienteRepository.findOne(id);
	}

	public List<Cliente> findByNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	public Cliente save(Cliente cliente) {
		Endereco endereco = cliente.getEndereco();
		endereco.setCliente(cliente);
		return clienteRepository.save(cliente);
	}
	
	public void delete(Integer id) {
		this.clienteRepository.delete(id);
	}


	
	
	

}
