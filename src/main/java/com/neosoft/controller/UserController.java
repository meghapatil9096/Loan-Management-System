package com.neosoft.controller;

import com.neosoft.dto.LoginDTO;
import com.neosoft.dto.SignupDTO;
import com.neosoft.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    String registerUser(@Valid @RequestBody SignupDTO request){
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@Valid @RequestBody LoginDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(request));
    }
}
