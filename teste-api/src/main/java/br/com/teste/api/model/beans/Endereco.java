package br.com.teste.api.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema="venda", name="endereco")
@SequenceGenerator(name="SEQ_ID_ENDERECO", sequenceName="venda.seq_id_endereco", allocationSize=1)
public class Endereco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_ENDERECO")
	private Long id;
	
	@NotNull
	@Size(min=3, max=50)
	@Column
	private String logradouro;
	
	@NotNull
	@Size(min=3, max=30)
	@Column
	private String bairro;
	
	@Column
	private String cidade;
	
	@Column
	@Enumerated(EnumType.STRING)
	private UF uf;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

//	public Cliente getCliente() {
//		return cliente;
//	}

//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}

}
