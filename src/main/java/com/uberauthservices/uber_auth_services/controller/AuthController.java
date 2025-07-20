package com.uberauthservices.uber_auth_services.controller;

import com.uberauthservices.uber_auth_services.Dto.PassengerDto;
import com.uberauthservices.uber_auth_services.Dto.PassengerSignUpRequestDto;
import com.uberauthservices.uber_auth_services.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto passengerSignUpRequestDto) {
        PassengerDto response = authService.signUpPassenger(passengerSignUpRequestDto);
        return new ResponseEntity<>(response ,HttpStatus.CREATED);
    }


}
