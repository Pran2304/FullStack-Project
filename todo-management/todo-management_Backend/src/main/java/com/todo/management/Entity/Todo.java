package com.todo.management.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment of ID
	private int id;
    
    @Column(nullable=false)
    private String tittle;
    
    @Column(nullable=false)
    private String description;
    

	private boolean completed;
    
	public Todo(int id, String tittle, String description, boolean completed) {
		this.id = id;
		this.tittle = tittle;
		this.description = description;
		this.completed = completed;
	}
	public Todo() {
	}
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
	@Override
	public String toString() {
		return "Todo [id=" + id + ", tittle=" + tittle + ", description=" + description + ", completed=" + completed
				+ "]";
	}
	
	
}
