package br.com.teste.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.teste.model.beans.ObjetoNaoEncontradoException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<Object> handleProdutoException(Exception ex, WebRequest request) {
		return this.handleExceptionInternal(ex, this.criaListaErros(ex.getMessage()), 
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
	}

	private List<Erro> criaListaErros(String... erros ) {
		List<Erro> lista = new ArrayList<>();
		for(String erro : erros) {
			lista.add(new Erro(erro));
		}
		return lista;
	}
	
	private class Erro {
		private String mensagem;
		
		public Erro(String mensagem) {
			super();
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		
		
	}
}

