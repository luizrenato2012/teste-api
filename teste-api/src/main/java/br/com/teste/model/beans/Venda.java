package br.com.teste.model.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Venda implements Serializable {
	
	private static final long serialVersionUID = -2285152440253541748L;

	private Integer id;
	
	private LocalDateTime dataHora;
	
	private Cliente cliente;
	
	private List<ItemVenda> itens;
	
	private SituacaoVenda situacao;
	
	private double valorTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public SituacaoVenda getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoVenda situacao) {
		this.situacao = situacao;
	}
	
	

}
