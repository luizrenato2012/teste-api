package br.com.teste.api.model.repository.filtro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.teste.api.model.beans.Cliente;
import br.com.teste.api.model.beans.Pessoa;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		//restricoes
		Predicate[] predicates=criaRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Cliente> query = manager.createQuery(criteria);
		
		adicionaRestricoesPaginacaoNaQuery(query,pageable);
		
		return new PageImpl (query.getResultList(), pageable, total(clienteFilter) );
	}

	@Override
	public Page<ResumoCliente> resumir(ClienteFilter clienteFilter, Pageable pageable) {
		return null;
	}
	
	private Predicate [] criaRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder,
			Root<Cliente> root) {
		List<Predicate> predicates = new ArrayList<>();
		

		if (!StringUtils.isEmpty(clienteFilter.getNome())) {
			Join<Cliente,Pessoa> join = root.join("pessoa");
			predicates.add(builder.like( // https://forum.hibernate.org/viewtopic.php?f=1&t=1044516
//					builder.lower(root.get("pessoa.nome")), "%" + clienteFilter.getNome().toLowerCase() + "%"));
					builder.lower(join.get("nome")), "%" + clienteFilter.getNome().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(clienteFilter.getCpf())) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("pessoa.cpf"), clienteFilter.getCpf()));
		}
		
		if (!StringUtils.isEmpty(clienteFilter.getCpf())) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("pessoa.cnpj"), clienteFilter.getCnpj()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}
	
	private void adicionaRestricoesPaginacaoNaQuery(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private long total(ClienteFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		Predicate[] predicates = criaRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
