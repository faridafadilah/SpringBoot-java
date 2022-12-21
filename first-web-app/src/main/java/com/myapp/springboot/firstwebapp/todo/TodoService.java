package com.myapp.springboot.firstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
  private static List<Todo> todos = new ArrayList<>();
  private static int Count = 0;

  static {
    todos.add(new Todo(++Count, "Farida", "Learn JS", LocalDate.now().plusYears(1), false));
    todos.add(new Todo(++Count, "Fadilah", "Learn Java", LocalDate.now().plusYears(2), false));
    todos.add(new Todo(++Count, "Hani", "Learn Kotlin", LocalDate.now().plusYears(3), true));
  }

  public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}

  public void addTodo(String username, String description, LocalDate targDate, boolean done) {
    Todo todo = new Todo(++Count, username, description, targDate, done);
    todos.add(todo);
  }

  public void deleteById(int id) {
    //todo.getId() == id
    // todo -> todo.getId() == id
    Predicate<? super Todo> predicate 
      = todo -> todo.getId() == id;
    todos.removeIf(predicate);
  }

  public Todo findById(int id) {
    Predicate<? super Todo> predicate 
      = todo -> todo.getId() == id;
      Todo todo = todos.stream().filter(predicate).findFirst().get();
      return todo;
    }

  public void updateTodo(@Valid Todo todo) {
    deleteById(todo.getId());
    todos.add(todo);
  }
}
