package com.todo.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.management.Entity.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo,Integer>{

}
