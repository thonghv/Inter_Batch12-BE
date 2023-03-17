package com.example.todoapi.controller;

import com.example.todoapi.constant.HttpCode;
import com.example.todoapi.entity.Todo;
import com.example.todoapi.entity.User;
import com.example.todoapi.model.ErrorResponseModel;
import com.example.todoapi.model.SuccessResponseModel;
import com.example.todoapi.service.TodoService;
import com.example.todoapi.service.UserService;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<?> getAll() {
    try {
      Object users = userService.getAll();
      SuccessResponseModel res = new SuccessResponseModel(new Date(), 200, "success", users);
      return ResponseEntity.ok(res);
    } catch (Exception e) {
      ErrorResponseModel res =
          new ErrorResponseModel(new Date(), 500, e.getMessage(), e.getLocalizedMessage());
      return ResponseEntity.internalServerError().body(res);
    }
  }

  @GetMapping(value = {"{id}", "{id}/"})
  public ResponseEntity<?> getOne(@PathVariable("id") String userID) {

    User user = userService.getOne(userID);
    if (user == null) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(
        new SuccessResponseModel(new Date(), HttpCode.OK.getValue(), HttpCode.OK.name(), user));
  }

  @PostMapping()
  public ResponseEntity<?> add(@RequestBody User newUser) {
    if (userService.add(newUser)) {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(
              new SuccessResponseModel(
                  new Date(),
                  HttpCode.CREATED.getValue(),
                  HttpCode.CREATED.name(),
                  userService.add(newUser)));
    }
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(
            new ErrorResponseModel(
                new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(), ""));
  }

  @PutMapping("{id}")
  public ResponseEntity<User> update(@PathVariable("id") String userId, @RequestBody User newUser) {
    return ResponseEntity.ok(userService.update(userId, newUser));
  }

  @PatchMapping(value = {"{id}", "{id}/"})
  public ResponseEntity<User> updateSomeField(
      @PathVariable("id") String userID, @RequestBody Map<String, Object> fields) {
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

  @PutMapping({"{id}/todos/{todoId}"})
  public Todo updateToDo(
      @PathVariable("id") String userId,
      @PathVariable("todoId") String todoId,
      @RequestBody Todo newData) {
    return todoService.update(userId, todoId, newData);
  }

  @DeleteMapping({"{id}/todos/{todoId}"})
  public Todo deleteTodo(@PathVariable("id") String userId, @PathVariable("todoId") String todoId) {
    return todoService.deleteOne(userId, todoId);
  }
}
