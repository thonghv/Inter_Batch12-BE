package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  UserRepo userRepo;
  public Object getAllUser() {
    return userRepo.findAll();
  }
}