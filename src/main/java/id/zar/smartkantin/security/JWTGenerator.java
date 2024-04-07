package id.zar.smartkantin.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTGenerator {
	
	public static String SECRET = "rahasiarahasiarahasiarahasiarahasiarahasiarahasiarahasiarahasiarahasia";
	public static SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public String generateToken(Authentication authentication)
	{
		String username = authentication.getName();
		Date currentDate = new Date();
//		currentDate.
		var umur = 1000 // ms 
				* 60 // s
				* 60 // menit
				* 24 // jam
				;
		Date expireDate = new Date(currentDate.getTime() + umur);
		
//		var key = Keys.hmacShaKeyFor(SECRET.getBytes());
		
		String token = Jwts.builder()
				.subject(username)
				.issuedAt(currentDate)
				.expiration(expireDate)
//				.signWith(SignatureAlgorithm.HS512, "rahasia")
				
				.signWith(KEY)
				.compact();
		
		return token;
	}
	
	public String getUsernameFromJWT(String token) {
//		var key = Keys.hmacShaKeyFor(SECRET.getBytes());
		var parser = Jwts.parser()
				.verifyWith(KEY)
				.build();
		var claims = parser.parseSignedClaims(token).getPayload();
		return claims.getSubject();
	}
	
	public boolean isTokenValid(String token)
	{
		try
		{
			Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("jwt kadaluarsa atau salah");
		}
		return false;
	}
}
