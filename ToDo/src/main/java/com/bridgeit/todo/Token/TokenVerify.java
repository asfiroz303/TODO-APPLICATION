/*package com.bridgeit.todo.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenVerify {

	String key="secretKey";
	
	//Sample method to validate and read the JWT
	@SuppressWarnings("unused")
	private void parseJWT(String jwt) {
	 
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(key)
	       .parseClaimsJws(jwt).getBody();
	    System.out.println("ID: " + claims.getId());
	    System.out.println("Subject: " + claims.getSubject());
	    System.out.println("Issuer: " + claims.getIssuer());
	    System.out.println("Expiration: " + claims.getExpiration());
	}

	@Override
	public String toString() {
		return "TokenVerify [key=" + key + "]";
	}

}


private static final String KEY = "application";

public static int verify(String token) {
try {
Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();

System.out.println("Claims--->"+claims.getExpiration());
return Integer.parseInt(claims.getIssuer());

} catch (Exception e) {

e.printStackTrace();
return 0;
}
}
}
*/