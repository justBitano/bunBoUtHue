package com.bunBoUtHue.foodStall.controller;

import com.bunBoUtHue.foodStall.dto.AuthenticationRequest;
import com.bunBoUtHue.foodStall.dto.AuthenticationResponse;
import com.bunBoUtHue.foodStall.dto.SignUpRequest;
import com.bunBoUtHue.foodStall.repository.UserRepository;
import com.bunBoUtHue.foodStall.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private UserRepository userRepository;

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> signUpUser(@RequestBody SignUpRequest signUpRequest){
       return ResponseEntity.ok(authService.createUser(signUpRequest));
    }

    @PostMapping("/sign-up-for-manager")
    public ResponseEntity<AuthenticationResponse> signUpManager(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.createManager(request));
    }
}
