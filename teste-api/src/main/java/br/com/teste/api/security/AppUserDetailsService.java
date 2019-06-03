package br.com.teste.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.teste.api.model.beans.Usuario;
import br.com.teste.api.model.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
		Usuario usuario= optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Email e/ou senha incorreto(s)"));
		UsuarioSistema usuarioSistema = new UsuarioSistema(usuario.getEmail(), usuario.getSenha(), authorities(usuario), usuario);
		return usuarioSistema;
	}

	private Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
		Set<GrantedAuthority> permissoes = new HashSet<>();
		usuario.getPermissoes()
			.forEach(permissao -> permissoes.add(new SimpleGrantedAuthority(permissao.getDescricao().toUpperCase())));
		return permissoes;
	}

}
