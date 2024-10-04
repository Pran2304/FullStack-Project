package com.todo.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.todo.management.security.JwtAuthenticationEntryPoint;
import com.todo.management.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
	
    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;
    
	public SpringSecurityConfig(UserDetailsService userDetailsService,
			JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter authenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf((csrf)->csrf.disable())
	      .authorizeHttpRequests((authorize) -> {
//            authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//            authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
//            authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
            authorize.requestMatchers("/api/auth/**").permitAll();
            authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
		
		http.exceptionHandling(exception -> exception
				.authenticationEntryPoint(authenticationEntryPoint));
		
		http.addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails pranjali =User.builder()//to build user
//				 .username("pranjali")
//				 .password(passwordEncoder().encode("password"))
//				 .roles("USER")
//				 .build();
//				
//		UserDetails admin =User.builder()//to build user
//				 .username("admin")
//				 .password(passwordEncoder().encode("admin"))
//				 .roles("ADMIN")
//				 .build();
//		
//		return new InMemoryUserDetailsManager(pranjali,admin);
//	}
	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}
