package com.auth.scalerauthservice.controllers;

import com.auth.scalerauthservice.dtos.LoginDto;
import com.auth.scalerauthservice.exceptions.NotFoundException;
import com.auth.scalerauthservice.exceptions.TokenGenerationException;
import com.auth.scalerauthservice.models.User;
import com.auth.scalerauthservice.repositories.UserRepository;
import com.auth.scalerauthservice.services.service_interfaces.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) throws NotFoundException, TokenGenerationException {

        Optional<User> optionalUser = userRepository.findByEmail(loginDto.getEmail());

        if (optionalUser.isEmpty()){
            throw new NotFoundException("User with email " + loginDto.getEmail() + " was not found!");
        }

        User user = optionalUser.get();

        Optional<String> optionalToken = authService.login(user);

        if (optionalToken.isEmpty()){
            throw new TokenGenerationException("Token was not generated!");
        }

        String token = optionalToken.get();

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestBody LoginDto loginDto) throws NotFoundException, TokenGenerationException {

        Optional<User> optionalUser = userRepository.findByEmail(loginDto.getEmail());

        if (optionalUser.isEmpty()){
            throw new NotFoundException("User with email " + loginDto.getEmail() + " was not found!");
        }

        User user = optionalUser.get();

        Optional<Boolean> optionalBoolean = authService.validate(user);

        if (optionalBoolean.isEmpty()){
            throw new TokenGenerationException("Token was not found!");
        }

        return new ResponseEntity<>(optionalBoolean.get(), HttpStatus.OK);
    }

}
