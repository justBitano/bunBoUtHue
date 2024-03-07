package com.bunBoUtHue.foodStall.service;

import com.bunBoUtHue.foodStall.dto.AuthenticationRequest;
import com.bunBoUtHue.foodStall.dto.AuthenticationResponse;
import com.bunBoUtHue.foodStall.dto.SignUpRequest;
import com.bunBoUtHue.foodStall.dto.UserDTO;

public interface AuthService {
    AuthenticationResponse createUser (SignUpRequest signUpRequest);

    AuthenticationResponse createManager (SignUpRequest signUpRequest);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
