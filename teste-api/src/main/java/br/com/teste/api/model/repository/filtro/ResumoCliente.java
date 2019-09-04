package br.com.teste.api.model.repository.filtro;

public class ResumoCliente {
	
	private Long id;
	
	private String cpfCnpj;
	
	private String cidade;
	
	private String uf;
	
	private String ddd;
	
	private String telefone;

	public ResumoCliente() {
		super();
	}

	public ResumoCliente(Long id, String cpfCnpj, String cidade, String uf, String ddd, String telefone) {
		super();
		this.id = id;
		this.cpfCnpj = cpfCnpj;
		this.cidade = cidade;
		this.uf = uf;
		this.ddd = ddd;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
