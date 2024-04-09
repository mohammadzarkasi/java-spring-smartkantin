package id.zar.smartkantin.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService svcUserDetail;
	@Autowired
	private JWTAuthEntryPoint jwtAuthEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http
//			.csrf(AbstractHttpConfigurer::disable)
			.csrf(c -> c.disable())
			
//			.csrf().disable()
			
			.exceptionHandling(c -> c.authenticationEntryPoint(jwtAuthEntryPoint))

//			.authenticationEntryPoint()
			
			
			.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//			.sessionCreationPolicy()
			
//			.authorizeRequests()
//			.authorizeHttpRequests((ahr) -> ahr.requestMatchers("/api/**").hasRole(""))
//			.authorizeHttpRequests((ahr) -> ahr.)
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/api/auth/**").permitAll()
					.requestMatchers("/api/public/**").permitAll()
					
//					.requestMatchers("/api/admin/**").authenticated()
					.requestMatchers("/api/admin/**").hasAnyAuthority("ADMIN")
//					.requestMatchers("/api/vendor/**").authenticated()
					.requestMatchers("/api/vendor/**").hasAnyAuthority("VENDOR")
//					.requestMatchers("/api/customer/**").authenticated()
					.requestMatchers("/api/customer/**").hasAnyAuthority("USER")
					

//					.requestMatchers("/api/**").authenticated()
					
					.requestMatchers("/swagger-ui/**").permitAll()
//					
//					.anyRequest().authenticated()
					.anyRequest().permitAll()
				)
				
//			.anyRequest().authenticated()
			
//			.and()
//			.httpBasic();
//			.httpBasic(Customizer.withDefaults())
			.httpBasic(c -> {})	
			;
		
		http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
//	@Bean
//	public UserDetailsService users()
//	{
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password("12345")
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user = User.builder()
//				.username("user")
//				.password("12345")
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin, user);
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		var result = authenticationConfiguration.getAuthenticationManager();
		System.out.println("auth manager: " + result);
		return result;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JWTAuthFilter jwtAuthFilter()
	{
		return new JWTAuthFilter();
	}
}
