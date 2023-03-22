package com.intern.hrmanagementapi.controller;

import com.intern.hrmanagementapi.constant.AuthEndpointConst;
import com.intern.hrmanagementapi.constant.MessageConst;
import com.intern.hrmanagementapi.model.AuthenticationRequestDto;
import com.intern.hrmanagementapi.model.DataResponseDto;
import com.intern.hrmanagementapi.model.RegisterRequestDto;
import com.intern.hrmanagementapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AuthEndpointConst.BASE_PATH)
@RequiredArgsConstructor
public class AuthenticationController {

  @Autowired
  private final AuthenticationService authenticationService;

  @PostMapping(value = {AuthEndpointConst.REGISTER})
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto req) {
    var response = authenticationService.register(req);
    return ResponseEntity.ok(
        DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
  }

  @PostMapping(value = {AuthEndpointConst.LOGIN})
  public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestDto req) {
    var response = authenticationService.authenticate(req);
    return ResponseEntity.ok(
        DataResponseDto.success(HttpStatus.OK.value(), MessageConst.SUCCESS, response));
  }
}
