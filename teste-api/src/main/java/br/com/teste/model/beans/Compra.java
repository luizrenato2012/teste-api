package br.com.teste.model.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Compra implements Serializable {
	
	private static final long serialVersionUID = -2285152440253541748L;

	private Integer id;
	
	private LocalDateTime dataHora;
	
	private Cliente cliente;
	
	private List<ItemCompra> itens;
	
	private SituacaoCompra situacao;
	
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

	public List<ItemCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public SituacaoCompra getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCompra situacao) {
		this.situacao = situacao;
	}
	
	

}
