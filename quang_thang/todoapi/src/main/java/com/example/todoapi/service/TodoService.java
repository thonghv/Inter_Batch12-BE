package com.example.todoapi.service;

import com.example.todoapi.Repo.TodoRepo;
import com.example.todoapi.entity.Todo;
import com.example.todoapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
  @Autowired TodoRepo todoRepo;

  public Object getAllByUserId(String userID) {
    return todoRepo.findAllByUserId(userID);
  }

  public Object getOneByUserId(String todoID, String userID) {
    return todoRepo.findByIdAndUserId(todoID, userID);
  }

  public Todo add(User user, Todo todo) {
    todo.setUser(user);
    return todoRepo.save(todo);
  }
}
