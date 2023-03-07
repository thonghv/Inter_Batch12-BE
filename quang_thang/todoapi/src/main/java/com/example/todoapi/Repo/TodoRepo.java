package com.example.todoapi.Repo;

import com.example.todoapi.entity.Todo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends CrudRepository<Todo, String> {
  List<Todo> findAllByUserId(String userID);

  Todo findByIdAndUserId(String todoID, String userID);
}
