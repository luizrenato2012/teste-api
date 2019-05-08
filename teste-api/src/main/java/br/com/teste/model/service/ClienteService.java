package br.com.teste.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.model.beans.Cliente;
import br.com.teste.model.beans.ObjetoNaoEncontradoException;
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
		if (cliente.getId()!=null) {
			this.validaCliente(cliente.getId());
		}
		return clienteRepository.save(cliente);
	}
	
	public void delete(Integer id) {
		if (id==null) {
			throw new ObjetoNaoEncontradoException("Id do objeto invalido");
		}
		this.validaCliente(id);
		Cliente cliente = this.clienteRepository.findOne(id);
		this.clienteRepository.delete(cliente);
	}

	private void validaCliente(Integer id) {
		Cliente cliente = this.clienteRepository.findOne(id);
		if (cliente==null) {
			throw new ObjetoNaoEncontradoException("Cliente id ["+ id + "] nao encontrado");
		}
	}

	
	
	

}
