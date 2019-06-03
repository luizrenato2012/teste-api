package br.com.teste.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.teste.api.model.beans.Usuario;

public class UsuarioSistema extends User {
	
	private Usuario usuario; 
	private static final long serialVersionUID = 1L;
	
	public UsuarioSistema(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Usuario usuario) {
		super(username, password, authorities);
		this.usuario=usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
