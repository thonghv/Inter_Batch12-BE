package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.EndpointConst;
import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.model.AuthenticationRequestDto;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.model.RegisterRequestDto;
import com.intern.hrmanagementapi.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = EndpointConst.AUTH_BASE_PATH)
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "The authentication API")
@CrossOrigin(maxAge = 3600)
public class AuthenticationController {

  @Autowired
  private final AuthenticationService authenticationService;

  @PostMapping(value = {EndpointConst.REGISTER})
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto req) {
    var response = authenticationService.register(req);
    return ResponseEntity.ok(
        DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
  }

  @PostMapping(value = {EndpointConst.LOGIN})
  public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestDto req) {
    var response = authenticationService.authenticate(req);
    return ResponseEntity.ok(
        DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
  }
}
