package br.com.teste.api.model.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TT")
public class TipoTelefone extends ObjetoValor {
	//COMERCIAL, CELULAR, RESIDENCIAL, RECADO
}
