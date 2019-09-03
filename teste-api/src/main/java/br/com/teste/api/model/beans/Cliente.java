package br.com.teste.api.model.beans;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(schema="venda", name="cliente")
@SequenceGenerator(name="SEQ_ID_CLIENTE", sequenceName="venda.seq_id_cliente",allocationSize=1)
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = -3912475183732151620L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_CLIENTE")
	private Integer id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name="id_situacao_cliente")
	private SituacaoCliente situacao;
	
	@Column(name="data_cadastro")
	private LocalDate dataCadastro;
	
	@Column
	@Lob
	private String observacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public SituacaoCliente getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCliente situacao) {
		this.situacao = situacao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	

}
