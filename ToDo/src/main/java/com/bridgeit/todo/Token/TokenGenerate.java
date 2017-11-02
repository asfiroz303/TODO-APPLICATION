package com.bridgeit.todo.Token;

import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class TokenGenerate {
	
	
	  String key="secretKey";
	  String compactJws=null;
	  
		@SuppressWarnings("unused")
	private String createJWT(String id, String issuer, String subject, long ttlMillis) {

	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date exp = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    Key key = MacProvider.generateKey();

	    String compactJws = Jwts.builder()
	    		.setExpiration(exp)
	            .signWith(SignatureAlgorithm.HS512, key)
	            .compact();

	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return "compactJws";
	}
	@Override
	public String toString() {
		return "TokenGenerate [key=" + key + ", compactJws=" + compactJws + "]";
	}
}
