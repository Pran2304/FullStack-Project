package com.todo.management.Service.lmpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.management.Dto.JwtAuthResponse;
import com.todo.management.Dto.LoginDto;
import com.todo.management.Dto.RegisterDto;
import com.todo.management.Entity.Role;
import com.todo.management.Entity.Role_user;
import com.todo.management.Exception.TodoAPIException;
import com.todo.management.Repository.RoleRepo;
import com.todo.management.Repository.Role_userRepo;
import com.todo.management.Service.AuthService;
import com.todo.management.security.JwtTokenProvider;



@Service
public class AuthServiceImpl implements AuthService {
    
	@Autowired
	private Role_userRepo role_userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String register(RegisterDto registerDto) {
		//check username in already existing db
		if(role_userRepo.existsByUsername(registerDto.getUsername())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Username already exists!");
		}
		
		//chcek email is already exist in db
		if(role_userRepo.existsByEmail(registerDto.getEmail())) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Email already exists!");
		}
		
		
		Role_user user=new Role_user();
		 user.setName(registerDto.getName());
		 user.setUsername(registerDto.getUsername());
	     user.setEmail(registerDto.getEmail());
	     user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
	
	     
	     Set<Role> roles=new HashSet<>();
	     Role userRole=roleRepo.findByName("ROLE_USER");
	     roles.add(userRole);
	     
	     user.setRoles(roles);
	     role_userRepo.save(user);
		 return "User Registered Successfully";
	
	}
	

	@Override
	public JwtAuthResponse login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(),
				loginDto.getPassword()
				));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtTokenProvider.generateToken(authentication);
		
		Optional<Role_user> userOptional=role_userRepo.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
		
	     String role=null;
	     if(userOptional.isPresent()) {
	    	 Role_user loggedInUser=userOptional.get();
	    	 Optional<Role> optionalRole=loggedInUser.getRoles().stream().findFirst();
	    	 
	    	 if(optionalRole.isPresent()) {
	    		 Role userRole=optionalRole.get();
	    		role=userRole.getName(); 
	    	}
	     }
	     JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
	     jwtAuthResponse.setRole(role);
	     jwtAuthResponse.setAccessToken(token);
	     
		return jwtAuthResponse;
	}

	
	public AuthServiceImpl() {
	}


	public AuthServiceImpl(Role_userRepo role_userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.role_userRepo = role_userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
}
