package br.com.teste.api.model.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Pessoa {
	
	private static final long serialVersionUID = 5732439887636986258L;
	
	@Column(name="razao_social")
	private String razaoSocial;
	
	@NotNull
	@Column
	private String cnpj;
	
	@Column(name="inscricao_estadual")
	private String inscricaoEstadual;
	
	@Column
	private String site;
	
	@OneToMany(mappedBy="pessoaJuridica")
	private List<Contato> contatos;
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getNomeFantasia() {
		return super.getNome();
	}
	public void setNomeFantasia(String nomeFantasia) {
		super.setNome(nomeFantasia);
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	
	
	public List<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
}
