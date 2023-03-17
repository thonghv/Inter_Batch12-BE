package com.example.todoapi.service;

import com.example.todoapi.Repo.UserRepo;
import com.example.todoapi.entity.User;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/** The type User service. */
@Service
public class UserService {

  /** The User repo. */
  @Autowired UserRepo userRepo;

  /**
   * Gets all.
   *
   * @return the all
   */
  public Object getAll() {
    return userRepo.findAll();
  }

  /**
   * Gets one.
   *
   * @param userID the user id
   * @return the one
   */
  public User getOne(String userID) {
    try {
      return userRepo.findById(userID).get();
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Add user.
   *
   * @param newUser the new user
   * @return the user
   */
  public boolean add(User newUser) {
    User existUser = getOne(newUser.getId());
    if (existUser == null) {
      userRepo.save(newUser);
      return true;
    }
    return false;
  }

  /**
   * Update user.
   *
   * @param user the user
   * @return the user
   */
  public User update(String userId, User user) {
    User updatedUser = getOne(userId);
    updatedUser.setName(user.getName());
    updatedUser.setEmail(user.getEmail());
    updatedUser.setTodos(user.getTodos());
    userRepo.save(updatedUser);
    return updatedUser;
  }

  public User update(String userId, Map<String, Object> fields) {
    Optional<User> updatedUser = userRepo.findById(userId);

    if (!updatedUser.isPresent()) {
      return null;
    }
    fields.forEach(
        (key, value) -> {
          Field field = ReflectionUtils.findField(User.class, key);
          field.setAccessible(true);
          ReflectionUtils.setField(field, updatedUser.get(), value);
        });
    userRepo.save(updatedUser.get());
    return updatedUser.get();
  }

  /**
   * Delete user.
   *
   * @param userID the user id
   * @return the user
   */
  public User delete(String userID) {
    User deletedUser = getOne(userID);
    userRepo.deleteById(userID);
    return deletedUser;
  }
}
