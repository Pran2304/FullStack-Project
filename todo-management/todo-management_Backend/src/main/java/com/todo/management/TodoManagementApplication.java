package com.todo.management;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TodoManagementApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TodoManagementApplication.class, args);
		System.out.println("Application started");
	}

	@Bean
	 public WebMvcConfigurer corsConfigurer() {
		 return new WebMvcConfigurer() {
			 @Override
			 public void addCorsMappings(CorsRegistry registry) {
				 registry.addMapping("/**").allowedOrigins("*");
			 }
		 };
	 }
	
}
