package com.example.web220810.service;

import com.example.web220810.dto.LoginRequest;
import com.example.web220810.dto.RequestMappingisterRequest;
import com.example.web220810.modl.User;
import com.example.web220810.repository.UserRepository;
import com.example.web220810.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;


    public void sigup(RequestMappingisterRequest reqisterRequest) {
        User user = new User();
        user.setUserName(reqisterRequest.getUsername());
        user.setEmail(reqisterRequest.getEmail());
        user.setPassword(encodePassword(reqisterRequest.getPassword()));
        userRepository.save(user);


    }

    /**
     *  通过ScurityConfig中的PasswordEncoder 进行加密密码
     * @param password 明文密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new AuthenticationResponse(jwtProvider.generateToken(authentication), loginRequest.getUserName());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
