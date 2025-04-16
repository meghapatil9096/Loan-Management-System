package com.neosoft.controller;

import com.neosoft.dto.SignupRequest;
import com.neosoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    String registerUser(@RequestBody SignupRequest request){
        return userService.registerUser(request);
    }
}
