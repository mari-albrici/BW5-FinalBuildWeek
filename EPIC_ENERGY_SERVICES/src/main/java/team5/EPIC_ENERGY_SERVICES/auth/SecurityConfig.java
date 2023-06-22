package team5.EPIC_ENERGY_SERVICES.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
	SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		http.cors(c -> c.disable());

		http.csrf(c -> c.disable());

		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/auth/register").permitAll());
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/auth/login").permitAll());
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.GET, "/users").authenticated());
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/users/{id}").authenticated());
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.GET,"/customers").authenticated());
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/customers/{id}").authenticated());
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.GET,"/invoice").authenticated());
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/invoice/{id}").authenticated());


		http.addFilterBefore(jwtAuthFilter,
				UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	@Bean
	PasswordEncoder pwEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}