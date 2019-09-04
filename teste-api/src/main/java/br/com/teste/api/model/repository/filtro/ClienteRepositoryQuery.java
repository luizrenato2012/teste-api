package br.com.teste.api.model.repository.filtro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.teste.api.model.beans.Cliente;

public interface ClienteRepositoryQuery {
	
public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);
	
	public Page<ResumoCliente> resumir (ClienteFilter clienteFilter, Pageable pageable);

}
