package br.com.teste.api.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenPreProcessorFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if ("/oauth/token".equals(httpRequest.getRequestURI()) && 
				"refresh_token".equals(httpRequest.getParameter("grant_type")) && 
				httpRequest.getCookies()!=null) {
			
			for(Cookie cookie : httpRequest.getCookies()) {
				if ("refreshToken".equals(cookie.getName())) {
					String refreshToken = cookie.getValue();
					request = new MyRequestWraper(httpRequest, refreshToken);
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	static class MyRequestWraper extends HttpServletRequestWrapper {
		
		private String refreshToken;

		public MyRequestWraper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> params = new ParameterMap<String,String[]>(this.getRequest().getParameterMap());
			params.put("refresh_token", new String[] {this.refreshToken});
			params.setLocked(true);
			return params;
		}
		
	}

}
