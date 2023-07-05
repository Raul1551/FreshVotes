package com.freshvotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.withUsername("raul1551")
				.password("Castuera15-")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/").permitAll()
				.requestMatchers("/login").permitAll()
				.anyRequest().hasRole("USER"));
		http
			.formLogin((form) -> form
					.loginPage("/login")
					.permitAll());
		http
			.logout((logout) -> logout
					.logoutUrl("/logout")
					.permitAll());

		return http.build();
	}
	
}
