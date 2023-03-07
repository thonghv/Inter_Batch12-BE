package com.example.todoapi.controller;

import com.example.todoapi.entity.Todo;
import com.example.todoapi.entity.User;
import com.example.todoapi.service.TodoService;
import com.example.todoapi.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/users", "/users/"})
public class UserController {
  @Autowired UserService userService;
  @Autowired TodoService todoService;

  @GetMapping
  public ResponseEntity<Object> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @GetMapping(value = {"{id}", "{id}/"})
  public ResponseEntity<User> getOne(@PathVariable("id") String userID) {
    return ResponseEntity.ok(userService.getOne(userID));
  }

  @PostMapping()
  public ResponseEntity<User> add(@RequestBody User newUser) {
    return ResponseEntity.ok(userService.add(newUser));
  }

  @PutMapping
  public ResponseEntity<User> update(@RequestBody User newUser) {
    return ResponseEntity.ok(userService.add(newUser));
  }

  @PatchMapping(value = {"{id}", "{id}/"})
  public ResponseEntity<User> updateSomeField(
      @PathVariable("id") String userID, Map<String, Object> fields) {
    return ResponseEntity.ok(userService.update(userID, fields));
  }

  @DeleteMapping(value = {"{id}", "{id}/"})
  public ResponseEntity<User> deleteOne(@PathVariable("id") String userID) {
    return ResponseEntity.ok(userService.delete(userID));
  }

  @GetMapping({"{id}/todos", "{id}/todos/"})
  public ResponseEntity<Object> getAllTodos(@PathVariable("id") String userID) {
    return ResponseEntity.ok().body(todoService.getAllByUserId(userID));
  }

  @GetMapping({"{id}/todos/{todoId}", "{id}/todos/{todoId}/"})
  public ResponseEntity<Object> getOneTodos(
      @PathVariable("todoId") String todoId, @PathVariable("id") String userID) {
    return ResponseEntity.ok().body(todoService.getOneByUserId(todoId, userID));
  }

  @PostMapping({"{id}/todos", "{id}/todos/"})
  public Todo addTodo(@PathVariable("id") String userID, @RequestBody Todo newTodo) {
    return todoService.add(userService.getOne(userID), newTodo);
  }
}
