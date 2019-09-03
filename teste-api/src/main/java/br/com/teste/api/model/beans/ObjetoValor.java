package br.com.teste.api.model.beans;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema="venda", name="tabela_valor")
@SequenceGenerator(name="SEQ_ID_OBJETO_VALOR", sequenceName="venda.seq_id_objeto_valor", allocationSize=1, initialValue=1)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminador")
public class ObjetoValor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_OBJETO_VALOR")
	private Long id;
	
	@Column
	private String descricao;
	
	@JsonIgnore
	@Column
	private String codigo;
	
	@JsonIgnore
	@Column
	private String tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public static enum Tipo {
		SITUACAO_CLIENTE, TIPO_TELEFONE
	}
	
}
