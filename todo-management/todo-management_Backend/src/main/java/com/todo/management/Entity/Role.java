package com.todo.management.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table 
public class Role {
	       @Id
	       @GeneratedValue(strategy=GenerationType.IDENTITY)
          private int id;
	       @Column(nullable=false)
          private String name;
	       
		public Role(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public Role() {
		}
		@Override
		public String toString() {
			return "Role [id=" + id + ", name=" + name + "]";
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
     
		
}
