package br.com.teste.model.repository.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.teste.model.beans.Produto;
import br.com.teste.model.repository.filtro.FiltroProduto;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page filtrar(FiltroProduto filtroProduto, Pageable pageAble) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		
		Root <Produto> rootFrom = criteria.from(Produto.class);
		Predicate[] predicates = this.criaRestricoes(builder,rootFrom, filtroProduto);
		criteria.where(predicates);
		TypedQuery<Produto> query = manager.createQuery(criteria);
		
		criaRestricoesPaginacaoNaQuery(query, pageAble);
		
		return new PageImpl<>(query.getResultList(), pageAble, totalPagina(filtroProduto));
	}

	private Long totalPagina(FiltroProduto filtroProduto) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root <Produto> rootFrom = criteria.from(Produto.class);
		
		Predicate[] predicates = this.criaRestricoes(builder, rootFrom, filtroProduto);
		criteria.where(predicates);
		criteria.select(builder.count(rootFrom));
		
		Query query = manager.createQuery(criteria);
		return (Long) query.getSingleResult();
	}

	private Predicate[] criaRestricoes(CriteriaBuilder builder, Root root,FiltroProduto filtroProduto) {
		List<Predicate> listaPredicate = new ArrayList<>();
		if (!StringUtils.isEmpty(filtroProduto.getDescricao() ) ) {
			listaPredicate.add(
					builder.like(
							builder.lower(root.get("descricao")), "%" + filtroProduto.getDescricao().toLowerCase() + "%"));
		}
		return  listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private void criaRestricoesPaginacaoNaQuery(TypedQuery<Produto> query, Pageable pageAble) {
		int paginaAtual = pageAble.getPageNumber();
		int totalRegistrosPorPagina = pageAble.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

}
