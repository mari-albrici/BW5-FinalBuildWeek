package team5.EPIC_ENERGY_SERVICES.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	JWTAuthFilter jwtAuthFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(c -> c.disable());

		http.csrf(c -> c.disable());

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/users/**").authenticated());
//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/users")
//				.hasAnyAuthority(Role.USER.name(), Role.ADMIN.name()));
//		http.authorizeHttpRequests(
//				auth -> auth.requestMatchers(HttpMethod.POST, "/users").hasAuthority(Role.ADMIN.name()));
//		http.authorizeHttpRequests(
//				auth -> auth.requestMatchers(HttpMethod.PUT, "/users").hasAuthority(Role.ADMIN.name()));
//		http.authorizeHttpRequests(
//				auth -> auth.requestMatchers(HttpMethod.PATCH, "/users").hasAuthority(Role.ADMIN.name()));
//		http.authorizeHttpRequests(
//				auth -> auth.requestMatchers(HttpMethod.DELETE, "/users").hasAuthority(Role.ADMIN.name()));
//		http.authorizeHttpRequests(auth -> auth
//			    .requestMatchers("/users/**").hasRole("USER")
//			    .requestMatchers(HttpMethod.GET, "/users").hasRole("USER")
//			    .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
//			    .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
//			    .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
//			    .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
//			    .anyRequest().authenticated()
//			);

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/customers/**").authenticated());

		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	@Bean
	PasswordEncoder pwEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}