package br.com.blas.hemoblas.service;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.blas.hemoblas.model.UsuarioJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${jwt.expire.hours}")
	private Long expireHours;

	@Value("${jwt.token.secret}")
	private String plainSecret;
	private String encodedSecret;

	@PostConstruct
	protected void init() {
		this.encodedSecret = generateEncodedSecret(this.plainSecret);
	}

	protected String generateEncodedSecret(String plainSecret) {
		if (StringUtils.isEmpty(plainSecret)) {
			throw new IllegalArgumentException("JWT secret cannot be null or empty.");
		}
		return Base64.getEncoder().encodeToString(this.plainSecret.getBytes());
	}

	protected Date getExpirationTime() {
		Date now = new Date();
		Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
		return new Date(expireInMilis + now.getTime());
	}

	protected UsuarioJwt getUser(String encodedSecret, String token) {
		Claims claims = Jwts.parser().setSigningKey(encodedSecret).parseClaimsJws(token).getBody();
		String userName = claims.getSubject();
		String role = (String) claims.get("role");

		UsuarioJwt securityUser = new UsuarioJwt();
		securityUser.setCpf(userName);
		securityUser.setPerfil(role);

		return securityUser;
	}

	public UsuarioJwt getUser(String token) {
		return getUser(this.encodedSecret, token);
	}

	protected String getToken(String encodedSecret, UsuarioJwt jwtUser) {
		Date now = new Date();
		return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(jwtUser.getCpf())
				.claim("role", jwtUser.getPerfil()).setIssuedAt(now).setExpiration(getExpirationTime())
				.signWith(SignatureAlgorithm.HS512, encodedSecret).compact();
	}

	public String getToken(UsuarioJwt jwtUser) {
		return getToken(this.encodedSecret, jwtUser);
	}
}
