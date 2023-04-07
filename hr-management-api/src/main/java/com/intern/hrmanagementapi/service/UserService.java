package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.exception.BadRequestException;
import com.intern.hrmanagementapi.exception.ObjectException;
import com.intern.hrmanagementapi.exception.ResourceAlreadyExistsException;
import com.intern.hrmanagementapi.exception.ResourceNotFoundException;
import com.intern.hrmanagementapi.model.ChangePasswordRequestDto;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.model.ResetPasswordDto;
import com.intern.hrmanagementapi.repo.UserRepo;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.intern.hrmanagementapi.type.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {

  @Autowired
  UserRepo userRepo;
  private final PasswordEncoder passwordEncoder;

  /**
   * Retrieves a list of users matching the specified search criteria.
   *
   * @param username the optional search keyword to filter the users by username
   * @param orderBy  the attribute to sort the users by, e.g. "id", "username", "createdAt", etc.
   * @param page     the page number to retrieve (0-based index)
   * @param size     the maximum number of items per page
   * @return a list of UserEntity objects matching the search criteria
   * @throws ResourceNotFoundException if no users are found matching the search criteria
   */
  public List<UserEntity> getUsers(String username, String orderBy, int page, int size) throws ResourceNotFoundException {
    Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());
    List<UserEntity> userEntityList;
    if (username != null) {
      userEntityList = userRepo.findByUsernameContaining(username, pageable);
    } else {
      userEntityList = userRepo.findAll(pageable).getContent();
    }
    if (userEntityList.isEmpty()) {
      throw new ResourceNotFoundException("No user found");
    }
    return userEntityList;
  }

  /**
   * Returns a user with the given id.
   *
   * @param id The id of the user to be returned.
   * @return UserEntity The user with the given id.
   * @throws ResourceNotFoundException Thrown if no user is found with the given id.
   */
  public UserEntity getUser(String id) throws ResourceNotFoundException {
    return userRepo.findById(UUID.fromString(id))
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
  }

  /**
   * Adds a new user to the system.
   *
   * @param entity The user to be added.
   * @return UserEntity The user that was added.
   * @throws ResourceAlreadyExistsException Thrown if a user already exists with the same email or username.
   */
  public UserEntity addUser(UserEntity entity) throws ResourceAlreadyExistsException {
    Optional<UserEntity> userEntity = userRepo.findByEmail(entity.getEmail());
    Optional<UserEntity> userEntity1 = userRepo.findByUsername(entity.getRightUsername());
    if (userEntity.isPresent()) {
      throw new ResourceAlreadyExistsException("User already exists with email: " + entity.getEmail());
    }
    if (userEntity1.isPresent()) {
      throw new ResourceAlreadyExistsException("User already exists with username: " + entity.getRightUsername());
    }
    return userRepo.save(entity);
  }

  /**
   * Updates an existing user in the system.
   *
   * @param newUser The updated user to be saved.
   * @return userEntity The updated user.
   * @throws ResourceNotFoundException      Thrown if no user is found with the id of the updated user.
   * @throws ResourceAlreadyExistsException Thrown if a user already exists with the same email.
   */
  public UserEntity updateUser(UserEntity newUser) throws ResourceNotFoundException, ResourceAlreadyExistsException {
    if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
      throw new ResourceAlreadyExistsException("User already exists with email: " + newUser.getEmail());
    }
    userRepo.findById(newUser.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + newUser.getId()));
    newUser.setUpdate_date(new Date());
    return userRepo.save(newUser);
  }

  /**
   * Deletes a user from the system with the given id.
   *
   * @param id The id of the user to be deleted.
   * @throws ResourceNotFoundException Thrown if no user is found with the given id.
   */
  public void deleteUser(String id) throws ResourceNotFoundException {
    Optional<UserEntity> user = userRepo.findById(UUID.fromString(id));
    if (user.isPresent()) {
      userRepo.deleteById(UUID.fromString(id));
    } else {
      throw new ResourceNotFoundException("User not found with id: " + id);
    }
  }

  /**
   * Retrieves a list of all active users from the user repository.
   *
   * @return List of UserEntity objects
   * @throws ResourceNotFoundException if no active users are found in the repository
   */
  public List<UserEntity> getActiveUsers() throws ResourceNotFoundException {
    List<UserEntity> userEntityList = userRepo.findByActive("ACTIVE");
    if (userEntityList.isEmpty()) {
      throw new ResourceNotFoundException("No user found");
    }
    return userEntityList;
  }

  /**
   * Activates a user account by setting the user's state to ACTIVE in the user repository.
   *
   * @param userEntity object to activate
   * @throws ResourceNotFoundException if the userEntity parameter is not found in the repository
   */
  public void activateUser(UserEntity userEntity) throws ResourceNotFoundException {
    Optional<UserEntity> user = userRepo.findById(userEntity.getId());
    if (user.isPresent()) {
      userEntity.setState(UserState.ACTIVE);
      userRepo.save(userEntity);
    } else {
      throw new ResourceNotFoundException("No user found");
    }
  }

  /**
   * Deactivates a user account by setting the user's state to INACTIVE in the user repository.
   *
   * @param userEntity UserEntity object to deactivate
   * @throws ResourceNotFoundException if the userEntity parameter is not found in the repository
   */
  public void deactivateUser(UserEntity userEntity) throws ResourceNotFoundException {
    Optional<UserEntity> user = userRepo.findById(userEntity.getId());
    if (user.isPresent()) {
      userEntity.setState(UserState.INACTIVE);
      userRepo.save(userEntity);
    } else {
      throw new ResourceNotFoundException("No user found");
    }
  }

  /**
   * Resets the password for a user identified by the email address provided in the ResetPasswordDto parameter.
   *
   * @param resetPasswordDto ResetPasswordDto object containing the email address, new password, and confirm password fields
   * @throws ResourceNotFoundException if no user is found with the provided email address
   * @throws BadRequestException       if the new password and confirm password fields in the resetPasswordDto parameter do not match
   */
  public void resetPassword(ResetPasswordDto resetPasswordDto) throws ResourceNotFoundException, BadRequestException {
    String email = resetPasswordDto.getEmail();
    String newPassword = resetPasswordDto.getNewPassword();
    String confirmPassword = resetPasswordDto.getConfirmPassword();
    UserEntity userEntity = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    if (!newPassword.equals(confirmPassword)) {
      throw new BadRequestException("Passwords do not match");
    }
    userEntity.setPassword(newPassword);
    userRepo.save(userEntity);
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