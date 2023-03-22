package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.entity.TokenEntity;
import com.intern.hrmanagementapi.entity.UserEntity;
import com.intern.hrmanagementapi.model.AuthenticationRequestDto;
import com.intern.hrmanagementapi.model.AuthenticationResponseDto;
import com.intern.hrmanagementapi.model.RegisterRequestDto;
import com.intern.hrmanagementapi.repo.TokenRepo;
import com.intern.hrmanagementapi.repo.UserRepo;
import com.intern.hrmanagementapi.type.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Authentication service.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

  @Autowired
  private final UserRepo userRepo;
  @Autowired
  private final TokenRepo tokenRepo;
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  /**
   * Register service
   *
   * @param req the register request body
   * @return the jwt token
   */
  public AuthenticationResponseDto register(RegisterRequestDto req) {

    UserEntity user = UserEntity.builder().username(req.getUsername()).email(req.getEmail())
        .password(passwordEncoder.encode(req.getPassword())).role(UserRole.USER).build();

    UserEntity savedUser = userRepo.save(user);
    String jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);

    return AuthenticationResponseDto.builder().token(jwtToken).build();
  }

  /**
   * Authentication serivce
   *
   * @param req the login request body
   * @return the jwt token
   */
  public AuthenticationResponseDto authenticate(AuthenticationRequestDto req) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

    UserEntity user = userRepo.findByEmail(req.getEmail()).orElseThrow();

    String jwtToken = jwtService.generateToken(user);
    revokedAllUserToken(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponseDto.builder().token(jwtToken).build();
  }

  private void saveUserToken(UserEntity user, String jwtToken) {
    TokenEntity token = TokenEntity.builder().user(user).token(jwtToken).expired(false)
        .revoked(false).build();
    tokenRepo.save(token);
  }

  private void revokedAllUserToken(UserEntity user) {
    var validUserToken = tokenRepo.findAllValidTokenByUserId(user.getId());
    if (validUserToken.isEmpty()) {
      return;
    }
    validUserToken.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });

    tokenRepo.saveAll(validUserToken);
  }
}
