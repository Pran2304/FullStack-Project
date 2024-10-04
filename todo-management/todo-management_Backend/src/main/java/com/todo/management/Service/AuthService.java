package com.todo.management.Service;

import com.todo.management.Dto.JwtAuthResponse;
import com.todo.management.Dto.LoginDto;
import com.todo.management.Dto.RegisterDto;

public interface AuthService {
    
	String register(RegisterDto registerDto);
	
	JwtAuthResponse login(LoginDto loginDto);
}
