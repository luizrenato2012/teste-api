package br.com.teste.api.model.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.teste.api.model.repository.filtro.FiltroProduto;

public interface ProdutoRepositoryQuery {

	public Page filtrar (FiltroProduto filtro , Pageable pageAble);
}
