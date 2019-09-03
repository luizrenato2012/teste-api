package br.com.teste.api.model.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EC")
public class EstadoCivil extends ObjetoValor{
	
	// CASADO, SOLTEIRO, DIVORCIADO

}
