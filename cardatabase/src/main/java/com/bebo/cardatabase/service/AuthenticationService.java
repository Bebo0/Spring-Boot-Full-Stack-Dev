package com.bebo.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

public class AuthenticationService {
	static final long EXPIRATIONTIME = 864_000_00; // 1 day in ms
	static final Key SIGNINKEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	//static final Key SIGNINKEY = Keys.secretKeyFor("SecretKey");
	static final String PREFIX = "Bearer";
	
	// Add token to Authorization header
	static public void addToken(HttpServletResponse res, String username) {
		String JwtToken = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis()
						+ EXPIRATIONTIME))
				.signWith(SIGNINKEY).compact();
		res.addHeader("Authorization", PREFIX + " " + JwtToken);
	res.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

	// Get token from Authorization header
	static public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SIGNINKEY)
					//.setSigningKey(SIGNINKEY)
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();
			
			if (user != null)
				return new UsernamePasswordAuthenticationToken(user, null, emptyList());
		}
		return null;
	}
}
