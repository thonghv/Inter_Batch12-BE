package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<UserEntity> getAllUser() throws ResourceNotFoundException{
        List<UserEntity> users = userRepo.findAll();

        if (users.isEmpty()){
            throw new ResourceNotFoundException("No users found");
        }
        return users;
    }

    public UserEntity getUser(String id) throws ResourceNotFoundException {
        return userRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserEntity addUser(UserEntity entity) throws ResourceAlreadyExistsException{
        Optional<UserEntity> userEntity = userRepo.findByEmail(entity.getEmail());
        if (userEntity.isPresent()) {
            throw new ResourceAlreadyExistsException("User already exists with email: " + entity.getEmail());
        }
        return userRepo.save(entity);
    }

    public UserEntity updateUser(UserEntity newUser) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("User already exists with email: " + newUser.getEmail());
        }
        userRepo.findById(newUser.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + newUser.getId()));
        newUser.setUpdate_date(new Date());
        return userRepo.save(newUser);
    }

    public void deleteUser(String id) throws ResourceNotFoundException {
        Optional<UserEntity> user = userRepo.findById(UUID.fromString(id));
        if (user.isPresent()) {
            userRepo.deleteById(UUID.fromString(id));
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

}