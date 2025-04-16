package com.neosoft.mapper;

import com.neosoft.dto.SignupRequest;
import com.neosoft.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User sigupRequestDtoToUser(SignupRequest signupRequest){
//        Mapping
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setRole(signupRequest.getRole());

        return user;
    }



}
