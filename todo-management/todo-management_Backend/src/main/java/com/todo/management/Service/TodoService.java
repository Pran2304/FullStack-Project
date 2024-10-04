package com.todo.management.Service;



import java.util.List;

import com.todo.management.Dto.TodoDto;



public interface TodoService {

     TodoDto addTodo(TodoDto todoDto);

	 List<TodoDto> getAllTodos();

	TodoDto getTodo(int id);

	TodoDto updateTodo(TodoDto todoDto,int id);

	void deleteTodo(int id);
	
	TodoDto completeTodo(int id);
	
	TodoDto IncompleteTodo(int id);
	
	
}
