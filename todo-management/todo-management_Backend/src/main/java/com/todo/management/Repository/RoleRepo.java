package com.todo.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.management.Entity.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {

	
	Role findByName(String name);
}
