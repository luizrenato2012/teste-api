package br.com.teste.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import br.com.teste.api.config.token.CustonTokenEnhancer;

@Profile("oauth2-security")
@Configuration
@EnableAuthorizationServer
public class AuthorizarionServer extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		final int ACCESS_TOKEN_VALIDADE = 60*10;
		final int REFRESH_TOKEN_VALIDADE = 60 * 60 * 24;
		clients.inMemory()
			.withClient("spaclient").secret("@angular)123")
				.scopes("read","write")
				.authorizedGrantTypes("password","refresh_token")
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDADE)
				.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDADE).
				and().
			withClient("mobile").secret("m0b1ll4")
				.scopes("read")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDADE)
				.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDADE);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
		
		endpoints
			.tokenStore(tokenStore())
			.tokenEnhancer(enhancerChain)
			.accessTokenConverter(jwtAccessTokenConverter())
			.reuseRefreshTokens(false)
			.authenticationManager(authenticationManager);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("signVenda");
		return converter;
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustonTokenEnhancer();
	}
	
}
