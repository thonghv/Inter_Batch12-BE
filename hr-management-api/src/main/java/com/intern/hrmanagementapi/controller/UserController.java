package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.EndpointConst;
import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.BadRequestException;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.logging.LoggerManager;
import com.intern.hrmanagementapi.mapping.UserMapper;
import com.intern.hrmanagementapi.model.ChangePasswordRequestDto;
import com.intern.hrmanagementapi.model.ResetPasswordDto;
import com.intern.hrmanagementapi.model.UserAddingDto;
import com.intern.hrmanagementapi.model.UserDto;
import com.intern.hrmanagementapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "The user API")
public class UserController {

  private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
  @Autowired
  UserService userService;
  @GetMapping
  public ResponseEntity<?> getUsers(
          @RequestParam(name = "username", required = false) String username,
          @RequestParam(name = "orderBy", defaultValue = "username") String orderBy,
          @RequestParam(name = "page", defaultValue = "0") int page,
          @RequestParam(name = "size", defaultValue = "10") int size) throws ResourceNotFoundException{
    List<UserEntity> users = userService.getUsers(username, orderBy, page, size);
    List<UserDto> userDto = userMapper.userEntityToUserDto(users);
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable String id) throws ResourceNotFoundException {
    LoggerManager.info("call api get /users/id");
    UserEntity userEntity = (UserEntity) userService.getUser(id);
    UserDto userDto = userMapper.userEntityToUserDto(userEntity);
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }
  @GetMapping("/active")
  public ResponseEntity<?> getActiveUsers() throws ResourceNotFoundException{
    List<UserEntity> userEntityList= userService.getActiveUsers();
    List<UserDto> userDtoList = userMapper.userEntityToUserDto(userEntityList);
    return new ResponseEntity<>(userDtoList,HttpStatus.OK);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
  }
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @PostMapping
  public ResponseEntity<?> addUser(@Valid @RequestBody UserAddingDto user) throws ResourceAlreadyExistsException{
    LoggerManager.info("call api Post /users");
    UserEntity userEntity = userMapper.addingUserToUserEntity(user);
    userService.addUser(userEntity);
    return new ResponseEntity<>(userEntity, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody UserAddingDto user) throws ResourceNotFoundException,ResourceAlreadyExistsException {
    LoggerManager.info("call api Put /users");
    UserEntity userEntity = userMapper.addingUserToUserEntity(user);
    userEntity.setId(UUID.fromString(id));
    userService.updateUser(userEntity);
    return new ResponseEntity<>(userEntity,HttpStatus.OK);
  }
  @PutMapping("/{id}/activate")
  public ResponseEntity<?> activateUser(@PathVariable String id) throws ResourceNotFoundException{
    UserEntity userEntity = userService.getUser(id);
    userService.activateUser(userEntity);
    return new ResponseEntity<>("User has been activated",HttpStatus.OK);
  }
  @PutMapping("/{id}/deactivate")
  public ResponseEntity<?> deactivateUser(@PathVariable String id) throws ResourceNotFoundException{
    UserEntity userEntity = userService.getUser(id);
    userService.deactivateUser(userEntity);
    return new ResponseEntity<>("User has been deactivated",HttpStatus.OK);
  }
  @PutMapping("/reset-password")
  public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto) throws ResourceNotFoundException, BadRequestException {
    userService.resetPassword(resetPasswordDto);
    return new ResponseEntity<>("Password reset successfully",HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable String id) throws ResourceNotFoundException {
    LoggerManager.info("call api Delete /user");
    userService.deleteUser(id);
    return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
  }
  @Operation(summary = "Change password", security = {@SecurityRequirement(name = "bearer-key")})
  @PostMapping(EndpointConst.User.CHANGE_PASSWORD)
  public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequestDto req) {
    var res = userService.changePassword(req);
    return ResponseEntity.ok(res);
  }
}
