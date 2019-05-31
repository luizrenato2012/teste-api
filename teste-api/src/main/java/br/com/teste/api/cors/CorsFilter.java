package br.com.teste.api.cors;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	private String origemPermitida="http://localhost:4200"; // TODO alterar pra ambientes diferentes
	private String ACCESS_CONTROL_ALLOW="Access-Control-Allow-";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setHeader(ACCESS_CONTROL_ALLOW+ "Origin", origemPermitida);
		res.setHeader(ACCESS_CONTROL_ALLOW+ "Credentials", "true");
		
		if(req.getMethod().equals("OPTIONS") && origemPermitida.equals(req.getHeader("Origin"))) {
			res.setHeader(ACCESS_CONTROL_ALLOW+"Methods", "GET, POST, PUT, DELETE, OPTIONS");
			res.setHeader(ACCESS_CONTROL_ALLOW+"Headers", "Content-Type, Accept, Authorization");
			res.setHeader(ACCESS_CONTROL_ALLOW+"Max-Age", "3600");
		} else {
			chain.doFilter(req, res);
		}
	}
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}


	@Override
	public void destroy() {
	}

}
