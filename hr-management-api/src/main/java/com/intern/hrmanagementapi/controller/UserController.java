package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.EndpointConst;
import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.logging.LoggerManager;
import com.intern.hrmanagementapi.mapping.UserMapper;
import com.intern.hrmanagementapi.model.ChangePasswordRequestDto;
import com.intern.hrmanagementapi.model.UserAddingDto;
import com.intern.hrmanagementapi.model.UserDto;
import com.intern.hrmanagementapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<?> getAllUser() {
    LoggerManager.info("call api get /users");
    List<UserEntity> userEntities = userService.getAllUser();
    List<UserDto> usersDto = userMapper.userEntityToUserDto(userEntities);

    return ResponseEntity.ok(usersDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable String id) throws ResourceNotFoundException {
    LoggerManager.info("call api get /users/id");
    UserEntity userEntity;
    try {
      userEntity = userService.getUser(id);
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.ok(ResponseEntity.ok(e.getMessage()));
    }

    UserDto userDto = userMapper.userEntityToUserDto(userEntity);
    return ResponseEntity.ok(userDto);
  }

  @PostMapping
  public ResponseEntity<?> addUser(@RequestBody UserAddingDto user)
      throws ResourceAlreadyExistsException {
    LoggerManager.info("call api Post /users");
    UserEntity userEntity = userMapper.addingUserToUserEntity(user);
    try {
      userService.addUser(userEntity);
    } catch (ResourceAlreadyExistsException e) {
      return ResponseEntity.ok(ResponseEntity.ok(e.getMessage()));
    }

    return ResponseEntity.ok(userEntity);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserAddingDto user)
      throws ResourceNotFoundException {
    LoggerManager.info("call api Put /users");
    UserEntity userEntity = userMapper.addingUserToUserEntity(user);
    userEntity.setId(UUID.fromString(id));
    try {
      userService.updateUser(userEntity);
    } catch (ResourceAlreadyExistsException | ResourceNotFoundException e) {
      return ResponseEntity.ok(e.getMessage());
    }
    return ResponseEntity.ok(userEntity);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable String id) throws ResourceNotFoundException {
    LoggerManager.info("call api Delete /user");
    try {
      userService.deleteUser(id);
      return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
    } catch (ResourceNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Change password", security = {@SecurityRequirement(name = "bearer-key")})
  @PostMapping(EndpointConst.User.CHANGE_PASSWORD)
  public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequestDto req) {
    var res = userService.changePassword(req);
    return ResponseEntity.ok(res);
  }
}
