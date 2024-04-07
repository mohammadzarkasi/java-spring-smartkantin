package id.zar.smartkantin.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthFilter extends OncePerRequestFilter{

	@Autowired
	private JWTGenerator jwtGenerator;
	
	@Autowired
	private CustomUserDetailsService customUserSvc;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getJwtFromRequest(request);
		if(token != null && jwtGenerator.isTokenValid(token) == true)
		{
			var username = jwtGenerator.getUsernameFromJWT(token);
			
			var userDetail = customUserSvc.loadUserByUsername(username);
			
			var authToken = new UsernamePasswordAuthenticationToken(userDetail.getUsername(), null, userDetail.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer ") == true)
		{
			return authHeader.substring(7);
		}
		return null;
	}

}
