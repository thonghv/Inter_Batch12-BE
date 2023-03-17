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

  public Todo update(String userId, String todoId, Todo newData) {

    Todo updatedTodo = todoRepo.findByIdAndUserId(todoId, userId);
    updatedTodo.setTitle(newData.getTitle());
    updatedTodo.setComplete(newData.isComplete());
    updatedTodo.setContent(newData.getContent());
    todoRepo.save(updatedTodo);
    return updatedTodo;
  }

  public Todo deleteOne(String userId, String todoId) {
    Todo deletedTodo = todoRepo.findByIdAndUserId(todoId, userId);
    todoRepo.delete(deletedTodo);
    return deletedTodo;
  }
}
