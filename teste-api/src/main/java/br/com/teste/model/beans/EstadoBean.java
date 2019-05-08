package br.com.teste.model.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface EstadoBean {
	
	@JsonIgnore
	default boolean isInclusao() {
		return getId()==null || getId()==0;
	}
	
	@JsonIgnore
	default boolean isEdicao() {
		return getId()!=null && getId()!=0;
	}
	
	abstract Integer getId();

}
