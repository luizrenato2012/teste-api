package br.com.teste.api.model.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SC")
public class SituacaoCliente extends ObjetoValor{

}
