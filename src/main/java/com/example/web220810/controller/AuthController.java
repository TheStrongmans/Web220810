package com.example.web220810.controller;

import com.example.web220810.dto.LoginRequest;
import com.example.web220810.dto.RequestMappingisterRequest;
import com.example.web220810.service.AuthService;
import com.example.web220810.service.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
//    @CrossOrigin
    @PostMapping("/siqnup")
    public ResponseEntity siqnup(@RequestBody RequestMappingisterRequest reqisterRequest)
    {
        authService.sigup(reqisterRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
//    @CrossOrigin
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest)
    {
        return authService.login(loginRequest);
    }

}
