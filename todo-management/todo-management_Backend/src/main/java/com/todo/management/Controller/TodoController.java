package com.todo.management.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.management.Dto.TodoDto;
import com.todo.management.Service.TodoService;



@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")

public class TodoController {
  
	@Autowired
	private TodoService todoService;
	@CrossOrigin("*")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
	     
		TodoDto savedTodo=todoService.addTodo(todoDto);
		return new ResponseEntity<>(savedTodo,HttpStatus.CREATED);
	}
	@CrossOrigin("*")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<List<TodoDto>> allTodos() {
		List<TodoDto> allTodo=todoService.getAllTodos();
//		return new ResponseEntity<>(allTodo,HttpStatus.OK);
		return ResponseEntity.ok(allTodo);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
	public ResponseEntity<TodoDto> getById(@PathVariable int id){
		TodoDto todoDto=todoService.getTodo(id);
		return ResponseEntity.ok(todoDto);
	}
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateById(@PathVariable int id,@RequestBody TodoDto todoDto){
    	 TodoDto updatedtodoDto=todoService.updateTodo(todoDto,id);
    	return ResponseEntity.ok(updatedtodoDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
       
    	todoService.deleteTodo(id);
    	return ResponseEntity.ok("Todo deleted");
    	 }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable int id){
    	
    	TodoDto updatedtodoDto=todoService.completeTodo(id);
    	return ResponseEntity.ok(updatedtodoDto);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/Incomplete")
    public ResponseEntity<TodoDto> IncompleteTodo(@PathVariable int id){
    	
    	TodoDto updatedtodoDto=todoService.IncompleteTodo(id);
    	return ResponseEntity.ok(updatedtodoDto);
    }
    
    
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	public TodoController() {
		
	}
}
