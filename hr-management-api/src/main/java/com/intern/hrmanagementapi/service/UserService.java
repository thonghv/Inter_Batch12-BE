package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.ObjectException;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.model.ChangePasswordRequestDto;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.repo.UserRepo;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  @Autowired
  UserRepo userRepo;
  private final PasswordEncoder passwordEncoder;

  public List<UserEntity> getAllUser() {
    return userRepo.findAll();
  }

  public UserEntity getUser(String id) throws ResourceNotFoundException {
    return userRepo.findById(UUID.fromString(id))
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
  }

  public UserEntity addUser(UserEntity entity) throws ResourceAlreadyExistsException {
    if (userRepo.findByEmail(entity.getEmail()).isPresent()) {
      throw new ResourceAlreadyExistsException(
          "User already exists with email: " + entity.getEmail());
    }
    return userRepo.save(entity);
  }

  public UserEntity updateUser(UserEntity newUser)
      throws ResourceNotFoundException, ResourceAlreadyExistsException {
    if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
      throw new ResourceAlreadyExistsException(
          "User already exists with email: " + newUser.getEmail());
    }
    userRepo.findById(newUser.getId()).orElseThrow(
        () -> new ResourceNotFoundException("User not found with id: " + newUser.getId()));
    newUser.setUsername(newUser.getEmail());
    if (newUser.getRightUsername() == null) {

      throw new ResourceNotFoundException("Username not found");
    }
    if (newUser.getEmail() == null) {
      throw new ResourceNotFoundException("Email not found");
    }
    if (newUser.getRole() == null) {
      throw new ResourceNotFoundException("Role not found");
    }
    if (newUser.getPassword() == null) {
      throw new ResourceNotFoundException("Password not found");
    }
    if (newUser.getState() == null) {
      throw new ResourceNotFoundException("State not found");
    }
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

  public DataResponseDto changePassword(ChangePasswordRequestDto req) throws ObjectException {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserEntity loggingUser = userRepo.findByEmail(auth.getName()).orElse(null);

    if (!passwordEncoder.matches(req.getCurrentPassword(), loggingUser.getPassword())) {
      throw new ObjectException(MessageConst.User.PW_INCORRECT, HttpStatus.BAD_REQUEST, null);
    }
    
    loggingUser.setPassword(passwordEncoder.encode(req.getNewPassword()));
    loggingUser.setUpdate_date(new Date());
    userRepo.save(loggingUser);

    return DataResponseDto.success(HttpStatus.OK.value(), MessageConst.User.CHANGE_PW_OK, null);
  }
}