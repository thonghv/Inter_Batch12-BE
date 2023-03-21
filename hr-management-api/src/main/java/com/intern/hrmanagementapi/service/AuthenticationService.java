package com.intern.hrmanagementapi.service;

import com.intern.hrmanagementapi.Model.AuthenticationRequest;
import com.intern.hrmanagementapi.Model.AuthenticationResponse;
import com.intern.hrmanagementapi.Model.RegisterRequest;
import com.intern.hrmanagementapi.entity.Token;
import com.intern.hrmanagementapi.entity.User;
import com.intern.hrmanagementapi.repo.TokenRepo;
import com.intern.hrmanagementapi.repo.UserRepo;
import com.intern.hrmanagementapi.type.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

  public AuthenticationResponse register(RegisterRequest req) {

    User user = User.builder().username(req.getUsername()).email(req.getEmail())
        .password(passwordEncoder.encode(req.getPassword())).role(UserRole.USER).build();

    User savedUser = userRepo.save(user);
    String jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest req) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

    User user = userRepo.findByEmail(req.getEmail()).orElseThrow();

    String jwtToken = jwtService.generateToken(user);
    revokedAllUserToken(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  private void saveUserToken(User user, String jwtToken) {
    Token token = Token.builder().user(user).token(jwtToken).expired(false).revoked(false).build();
    tokenRepo.save(token);
  }

  private void revokedAllUserToken(User user) {
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
