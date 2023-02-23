package com.example.api.service;

import com.example.api.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 * {@summary hihi}
 *
 * @author Devet
 */
@Service
public class UserService {
  private static final ArrayList<User> users = new ArrayList<>();

  static {
    users.add(new User("uuid1", "Bui Quang Thang", "bqt1", "bqt@gmail.com"));
    users.add(new User("uuid2", "Bui Quang Thang", "bqt2", "bqt@gmail.com"));
  }

  public String writeToJsonFile() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.writeValue(Paths.get("users.json").toFile(), users);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return "Wrote";
  }

  public void jsonToList() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      User ur = mapper.readValue(new File("users.json"), User.class);
      System.out.print(ur.toString());
    } catch (Exception e) {

    }
  }

  public User getOne(String userId) {
    for (User u : users) {
      if (u.getId().equals(userId)) {
        return u;
      }
    }
    return null;
  }

  public ArrayList<User> getAll() {
    return users;
  }

  public String addOne(User user) {
    for (User u : users) {
      if (u.getUsername().equals(user.getUsername()) || u.getId().equals(user.getId())) {
        return "User was exist";
      }
    }
    users.add(user);
    return "A user has been added";
  }

  public User update(User newData) {
    for (User u : users) {
      if (u.getId().equals(newData.getId())) {
        for (User u2 : users) {
          if (u2.getUsername().equals(newData.getUsername()) && !u.getId().equals(u2.getId())) {
            return null;
          }
        }
        u.setEmail(newData.getEmail());
        u.setFullname(newData.getFullname());
        u.setUsername(newData.getUsername());
        return newData;
      }
    }
    return null;
  }

  public User deleteById(String userId) {
    for (User u : users) {
      if (u.getId().equals(userId)) {
        users.remove(u);
      }
      return u;
    }
    return null;
  }

  public User deleteByUsername(String username) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        users.remove(u);
      }
      return u;
    }
    return null;
  }
}
