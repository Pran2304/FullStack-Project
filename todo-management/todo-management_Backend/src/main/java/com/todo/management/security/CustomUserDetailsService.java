package com.todo.management.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.management.Entity.Role_user;
import com.todo.management.Repository.Role_userRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
	private Role_userRepo role_userRepo;
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		 
		Role_user user=role_userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(()->new UsernameNotFoundException("User not exist by username or email"));
		
			
			
			Set<GrantedAuthority> authorities = user.getRoles().stream()
	                .map((role) -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toSet());

	        return new org.springframework.security.core.userdetails.User(
	                usernameOrEmail,
	                user.getPassword(),
	                authorities
	        );	
	}
	public CustomUserDetailsService(Role_userRepo role_userRepo) {
		this.role_userRepo = role_userRepo;
	}
    
	
}
