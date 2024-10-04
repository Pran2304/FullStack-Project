package com.todo.management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.management.Dto.JwtAuthResponse;
import com.todo.management.Dto.LoginDto;
import com.todo.management.Dto.RegisterDto;
import com.todo.management.Service.AuthService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	//build register Rest Api
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		String response=authService.register(registerDto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
		JwtAuthResponse jwtAuthResponse=authService.login(loginDto);
		
		return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
	}
}
