package br.com.teste.api.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
	}

//	private List<Erro> criaListaErros(String... erros ) {
//		List<Erro> lista = new ArrayList<>();
//		for(String erro : erros) {
//			lista.add(new Erro(erro));
//		}
//		return lista;
//	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemUsuario = ex.getMessage();
		String mensagemDesenvolvedor = messageSource.getMessage("mensagem-invalida", null,LocaleContextHolder.getLocale());
		Erro erro = new Erro(mensagemDesenvolvedor, mensagemUsuario);
		return this.handleExceptionInternal(ex, erro , headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = this.criaListaErros(ex.getBindingResult());
		return this.handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	private List<Erro> criaListaErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		String mensagemUsuario = "";
		String mensagemDesenvolvedor = "";
		
		for(FieldError fieldError: bindingResult.getFieldErrors()) {
			mensagemUsuario = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			mensagemDesenvolvedor= fieldError.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor ));
		}
		return erros;
	}


	private class Erro {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}

		
	}
}

