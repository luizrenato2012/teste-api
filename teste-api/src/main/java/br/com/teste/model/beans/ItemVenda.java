package br.com.teste.model.beans;

import java.io.Serializable;

public class ItemVenda implements Serializable {
	
	private static final long serialVersionUID = 4795797482322027561L;

	private Integer id;
	
	private Integer sequencial;
	
	private Produto produto;
	
	private double valorUnitario;
	
	private double quantidade;
	
	private double valorTotal;
	
	private Venda compra;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valor) {
		this.valorUnitario = valor;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Venda getCompra() {
		return compra;
	}

	public void setCompra(Venda compra) {
		this.compra = compra;
	}
	
}
