package com.todo.management.Dto;

public class TodoDto {
  private int id;
  private String tittle;
  private String description;
  private boolean completed;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTittle() {
	return tittle;
}
public void setTittle(String tittle) {
	this.tittle = tittle;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public boolean isCompleted() {
	return completed;
}
public void setCompleted(boolean completed) {
	this.completed = completed;
}
public TodoDto(int id, String tittle, String description, boolean completed) {
	this.id = id;
	this.tittle = tittle;
	this.description = description;
	this.completed = completed;
}
public TodoDto() {
}
@Override
public String toString() {
	return "TodoDto [id=" + id + ", tittle=" + tittle + ", description=" + description + ", completed=" + completed
			+ "]";
}
  
  
}
