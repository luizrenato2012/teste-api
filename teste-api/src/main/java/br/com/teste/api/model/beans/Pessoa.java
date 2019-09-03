package br.com.teste.api.model.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//https://stackoverflow.com/questions/50520334/spring-boot-jackson-databind-configure-inheritance
@JsonTypeInfo(
	 use = JsonTypeInfo.Id.NAME, 
	  include = JsonTypeInfo.As.PROPERTY, 
	  property = "type")
@JsonSubTypes({ 
	  @Type(value = PessoaFisica.class, name = "pf"), 
	  @Type(value = PessoaJuridica.class, name = "pj") 
	})
@Entity
@Table(schema="venda", name="pessoa")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
@SequenceGenerator(name="SEQ_ID_PESSOA", sequenceName="venda.seq_id_pessoa", allocationSize=1, initialValue=1)
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = -5992090106176216771L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_PESSOA")
	private Long id;
	
	@NotNull
	@Size(min=3, max=50)
	@Column
	private String nome;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} )
	@JoinColumn(name="id_telefone_1")
	private Telefone telefone1;
	
	@OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} )
	@JoinColumn(name="id_telefone_2")
	private Telefone telefone2;
	
	@Valid
	@OneToOne(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} )
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	
	@Column
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Telefone getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(Telefone telefone1) {
		this.telefone1 = telefone1;
	}

	public Telefone getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(Telefone telefone2) {
		this.telefone2 = telefone2;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
