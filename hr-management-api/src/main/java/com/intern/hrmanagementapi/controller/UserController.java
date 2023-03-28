package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.logging.LoggerManager;
import com.intern.hrmanagementapi.mapping.UserMapper;
import com.intern.hrmanagementapi.model.UserAddingDto;
import com.intern.hrmanagementapi.model.UserDto;
import com.intern.hrmanagementapi.service.UserService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "The user API")
public class UserController {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser() throws ResourceNotFoundException {
        LoggerManager.info("call api get /users");
        List<UserEntity> userEntities = userService.getAllUser();
        List<UserDto> usersDto = userMapper.userEntityToUserDto(userEntities);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) throws ResourceNotFoundException {
        LoggerManager.info("call api get /users/id");
        UserEntity userEntity = (UserEntity) userService.getUser(id);
        UserDto userDto = userMapper.userEntityToUserDto(userEntity);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) throws ResourceNotFoundException {
        LoggerManager.info("call api Delete /user");
            userService.deleteUser(id);
            return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
    }
}
