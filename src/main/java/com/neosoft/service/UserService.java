package com.neosoft.service;

import com.neosoft.dto.LoginRequest;
import com.neosoft.dto.SignupRequest;

public interface UserService {

    String registerUser(SignupRequest signupRequest);


}
