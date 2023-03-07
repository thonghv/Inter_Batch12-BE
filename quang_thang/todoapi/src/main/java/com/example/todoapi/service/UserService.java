package com.example.todoapi.service;

import com.example.todoapi.Repo.UserRepo;
import com.example.todoapi.entity.User;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    return userRepo.findById(userID).get();
  }

  /**
   * Add user.
   *
   * @param newUser the new user
   * @return the user
   */
  public User add(User newUser) {
    return userRepo.save(newUser);
  }

  /**
   * Update user.
   *
   * @param user the user
   * @return the user
   */
  public User update(User user) {
    return userRepo.save(user);
  }

  public User update(String userID, Map<String, Object> fields) {
    User user = getOne(userID);
    return userRepo.save(user);
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
