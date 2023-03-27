package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

  @Autowired
  UserRepo userRepo;

  public List<UserEntity> getAllUser() {
    return userRepo.findAll();
  }

  public UserEntity getUser(String id) throws ResourceNotFoundException {
    return userRepo.findById(UUID.fromString(id))
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
  }

  public UserEntity addUser(UserEntity entity) throws ResourceAlreadyExistsException {
    if (userRepo.findByEmail(entity.getEmail()).isPresent()) {
      throw new ResourceAlreadyExistsException("User already exists with email: " + entity.getEmail());
    }
    return userRepo.save(entity);
  }

  public UserEntity updateUser(UserEntity newUser) throws ResourceNotFoundException, ResourceAlreadyExistsException{
    if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
      throw new ResourceAlreadyExistsException("User already exists with email: " + newUser.getEmail());
    }
    userRepo.findById(newUser.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + newUser.getId()));
    newUser.setUsername(newUser.getEmail());
    if(newUser.getRightUsername() == null){

      throw new ResourceNotFoundException("Username not found");
    }
    if(newUser.getEmail() == null){
      throw new ResourceNotFoundException("Email not found");
    }
    if(newUser.getRole() == null){
      throw new ResourceNotFoundException("Role not found");
    }
    if(newUser.getPassword() == null){
      throw new ResourceNotFoundException("Password not found");
    }
    if(newUser.getState() == null){
      throw new ResourceNotFoundException("State not found");
    }
    newUser.setUpdate_date(new Date());
    return userRepo.save(newUser);
  }

  public void deleteUser(String id) throws ResourceNotFoundException{
   Optional<UserEntity> user = userRepo.findById(UUID.fromString(id));
   if(user.isPresent()){
     userRepo.deleteById(UUID.fromString(id));
   }
   else {throw new ResourceNotFoundException("User not found with id: " + id);}
  }

}