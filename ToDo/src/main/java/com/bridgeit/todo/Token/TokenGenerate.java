package com.bridgeit.todo.Token;

import java.util.Date;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerate {

	private String key = "secretKey";
	String compactJwt = null;
	
	public String generate(int id) {
		long times = 1000 * 60;
		Date issueDate = new Date(times);
		Date expDate = new Date(issueDate.getTime() + times);
		
		JwtBuilder builder = Jwts.builder().
				setIssuedAt(issueDate)
				.setExpiration(expDate)
				.setIssuer(String.valueOf(id))
				.signWith(SignatureAlgorithm.HS256, key);
		         String compactJwt = builder.compact();

		// Builds the JWT and serializes it to a compact, URL-safe string
		return compactJwt;
	}
}
