package com.clayfin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.clayfin.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// SecurityFilterChain-HttpSecurity class

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(t -> t.disable())
                .authorizeHttpRequests(t -> t.requestMatchers("/public/**").permitAll().requestMatchers("/*").permitAll()
                        .requestMatchers("/user/**").hasRole("USER").requestMatchers("/admin/**").hasRole("ADMIN"))
                .formLogin(login -> login.loginPage("/login").permitAll());
		       

		return http.build();
	}
	
	 @Bean
	   DaoAuthenticationProvider authProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(customUserDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

	// UserDetailsManager->User class

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
