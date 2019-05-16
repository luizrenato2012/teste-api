package br.com.teste.api.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> { // intercepta responses com token oauth2

	@Override // indica qual metodo sera interceptado
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		//acrescentar cookie com refreshtoken
		HttpServletRequest httpRequest = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse httpResponse = ((ServletServerHttpResponse) response).getServletResponse();
		String refreshToken = body.getRefreshToken().getValue();
		acrescentaRefreshTokenAoCookie(refreshToken, httpRequest, httpResponse);
		
		//retirar refresh token 
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		retiraRefreshToken(token);
		return token;
	}

	private void retiraRefreshToken(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}

	private void acrescentaRefreshTokenAoCookie(String refreshToken, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(false); // TOT alterar pra tru em producao
		cookie.setPath(request.getContextPath()+"/oauth/token");
		cookie.setMaxAge( 60 * 60 * 24);
		response.addCookie(cookie);
	}

}
