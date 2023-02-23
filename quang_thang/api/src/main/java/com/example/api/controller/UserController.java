package com.example.api.controller;

import com.example.api.entity.User;
import com.example.api.service.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public ResponseEntity<ArrayList<User>> getAll() {

    return new ResponseEntity<>(userService.getAll(), new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<User> getOne(@PathVariable("id") String userId) {
    return new ResponseEntity<>(userService.getOne(userId), new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping
  public String addOne(@RequestBody User newUser) {
    return userService.addOne(newUser);
  }

  @PutMapping
  public ResponseEntity<User> update(@RequestBody User newUserData) {
    User updatedUser = userService.update(newUserData);
    return updatedUser == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Object> delete(@PathVariable("id") String userId) {
    userService.deleteById(userId);
    return ResponseEntity.noContent().build();
  }
}
