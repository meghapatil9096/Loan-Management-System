package com.neosoft.service;

import com.neosoft.dto.LoginRequest;
import com.neosoft.dto.SignupRequest;
import com.neosoft.entity.User;
import com.neosoft.mapper.UserMapper;
import com.neosoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String registerUser(SignupRequest request) {
//  check if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent())
        {
            return "Email is Already in Use!";
        }
        User user = userMapper.sigupRequestDtoToUser(request);

        userRepository.save(user);
        return "User Registered Successfully!";
    }

    @Override
    public String login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if(userOptional.isPresent())
        {
            User user= userOptional.get();
            //simple password check
            if (user.getPassword().equals(request.getPassword()))
            {
                return "Login Successfully!";
            }
            else {
                return "Incorrect Password!";
            }
        } else {
            return "User Not Found!..";
        }

    }
}
