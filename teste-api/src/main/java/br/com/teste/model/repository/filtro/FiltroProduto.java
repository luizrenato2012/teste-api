package br.com.teste.model.repository.filtro;

public class FiltroProduto {
	
	private String descricao;

	public FiltroProduto(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	public FiltroProduto() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

}
