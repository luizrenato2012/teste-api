package br.com.teste.api.model.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema="venda", name="contato")
@SequenceGenerator(name="SEQ_ID_CONTATO", sequenceName="venda.seq_id_contato", allocationSize=1, initialValue=1)
public class Contato implements Serializable {
	
	private static final long serialVersionUID = -7580451835093496570L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_CONTATO")
	private Long id;
	
	@NotNull
	@Column	
	private String nome;
	
	@Column
	private String setor;
	
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="id_telefone")
	private Telefone telefone;
	
	@JsonIgnore // https://pt.stackoverflow.com/questions/242288/infinite-recursion-stackoverflowerror-erro-ao-listar-produtos-com-categorias
	@ManyToOne
	@JoinColumn(name="id_pessoa_juridica")
	private PessoaJuridica pessoaJuridica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	

}
