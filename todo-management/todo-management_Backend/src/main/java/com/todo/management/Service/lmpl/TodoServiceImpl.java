package com.todo.management.Service.lmpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.management.Dto.TodoDto;
import com.todo.management.Entity.Todo;
import com.todo.management.Exception.ResourceNotFoundException;
import com.todo.management.Repository.TodoRepo;
import com.todo.management.Service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TodoRepo todoRepo;
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
	  
		Todo todo=modelMapper.map(todoDto, Todo.class);
		
		Todo savedTodo=todoRepo.save(todo);
		
		TodoDto savedTodoDto=modelMapper.map(savedTodo, TodoDto.class);
		return savedTodoDto;
	}

	@Override
	public List<TodoDto> getAllTodos() {
		
		List<Todo> todos =todoRepo.findAll();
		
		return todos.stream().map((todo)->modelMapper.map(todo,TodoDto.class))
				.collect(Collectors.toList());
	}

	
	@Override
	public TodoDto getTodo(int id) {
		
		Todo todo=todoRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo Not found"+id));
				
		TodoDto todoDto=modelMapper.map(todo,TodoDto.class);
		return todoDto ;
	}


	
	@Override
	public TodoDto updateTodo(TodoDto todoDto,int id) {

		Todo todo=todoRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo Not found"+id));
		

		todo.setTittle(todoDto.getTittle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		
		Todo updatedTodo=todoRepo.save(todo);
		TodoDto updatedTodoDto=modelMapper.map(updatedTodo, TodoDto.class);
		return updatedTodoDto;
	}

	
	@Override
	public void deleteTodo(int id) {
		
		todoRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo Not found"+id));
		
		todoRepo.deleteById(id);
		
	}
	
	
	
	public TodoServiceImpl(ModelMapper modelMapper, TodoRepo todoRepo) {
		this.modelMapper = modelMapper;
		this.todoRepo = todoRepo;
	}

	public TodoServiceImpl() {
	}

	@Override
	public TodoDto completeTodo(int id) {
		Todo todo=todoRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo Not found"+id));
		
		todo.setCompleted(Boolean.TRUE) ;
		
		Todo updatedTodo=todoRepo.save(todo);
		
		TodoDto updatedTodoDto=modelMapper.map(updatedTodo, TodoDto.class);
		return updatedTodoDto;
	}

	@Override
	public TodoDto IncompleteTodo(int id) {
		Todo todo=todoRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo Not found"+id));
		
		todo.setCompleted(Boolean.FALSE) ;
		
		Todo updatedTodo=todoRepo.save(todo);
		
		TodoDto updatedTodoDto=modelMapper.map(updatedTodo, TodoDto.class);
		return updatedTodoDto;
	}

	

	
	
}
