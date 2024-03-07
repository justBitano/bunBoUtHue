package com.bunBoUtHue.foodStall.service.imple;

import com.bunBoUtHue.foodStall.dto.AuthenticationRequest;
import com.bunBoUtHue.foodStall.dto.AuthenticationResponse;
import com.bunBoUtHue.foodStall.dto.SignUpRequest;
import com.bunBoUtHue.foodStall.enums.TokenType;
import com.bunBoUtHue.foodStall.enums.UserRole;
import com.bunBoUtHue.foodStall.model.Token;
import com.bunBoUtHue.foodStall.model.User;
import com.bunBoUtHue.foodStall.repository.TokenRepository;
import com.bunBoUtHue.foodStall.repository.UserRepository;
import com.bunBoUtHue.foodStall.service.AuthService;
import com.bunBoUtHue.foodStall.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private  AuthenticationManager authenticationManager;



    public AuthenticationResponse createUser(SignUpRequest signUpRequest){

        User user  = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        var savedUser = userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse createAmin(SignUpRequest signUpRequest){

        User user  = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        signUpRequest.setPassword("1234");
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(UserRole.ADMIN);
        var savedUser = userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public boolean exitsAdmin(){
        var result = userRepository.findByRoleAdmin();
        if(result != null){
            return true;
        }
        return false;
    }
    public AuthenticationResponse createManager(SignUpRequest signUpRequest){

        User user  = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(UserRole.MANAGER);
        var savedUser = userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    private void revokeAllUserTokens(User  user){
        var validToken = tokenRepository.findAllValidTokenByUser(user.getId());
        if(validToken.isEmpty())
            return;

        validToken.forEach(t ->  {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtToken);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findFirstByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


}
