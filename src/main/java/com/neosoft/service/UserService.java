package com.neosoft.service;

import com.neosoft.dto.LoginRequest;
import com.neosoft.dto.SignupRequest;

public interface UserService {

//    SignUp Method
    String registerUser(SignupRequest signupRequest);
//    Login Method
    String login(LoginRequest request);
}
