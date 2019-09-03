package br.com.teste.api.model.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SV")
public class SituacaoVenda extends ObjetoValor{
	
	//ABERTA,FECHADA,PAGA,PENDENTE
}
