package com.todo.management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.management.Entity.Role_user;
@Repository
public interface Role_userRepo extends JpaRepository<Role_user,Long> {
   
	Optional<Role_user> findByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	Optional<Role_user> findByUsernameOrEmail(String username,String email);
	
	Boolean existsByUsername(String username);
	
}
