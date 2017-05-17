package br.com.blas.hemoblas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.blas.hemoblas.model.UsuarioJwt;
import br.com.blas.hemoblas.service.JwtService;
import io.jsonwebtoken.JwtException;

@Component
public class JwtFilter implements Filter {

	@Autowired
	private JwtService jwtService;

	@Value("${jwt.auth.header}")
	private String authHeader;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Habilita a Injeção de Dependência no Filtro
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());

	}

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeaderVal = request.getHeader(authHeader);

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(req, res);
		} else {
			if (authHeaderVal == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			try {
				UsuarioJwt usuarioJwt = jwtService.getUser(authHeaderVal);
				request.setAttribute("jwtUser", usuarioJwt);
			} catch (JwtException e) {
				response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				return;
			}

			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {

	}

}
