package com.neosoft.service.user;

import com.neosoft.dto.user.GetAllUserDTO;
import com.neosoft.dto.user.LoginDTO;
import com.neosoft.dto.user.SignupDTO;
import com.neosoft.dto.user.UpdateUserDTO;
import com.neosoft.entity.User;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.mapper.user.GetAllUserMapper;
import com.neosoft.mapper.user.UpdateMapper;
import com.neosoft.mapper.user.UserSignupMapper;
import com.neosoft.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSignupMapper userSignupMapper;

    @Override
    public String registerUser(SignupDTO request) {
//  check if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new RuntimeException("");
        }

        User user = userSignupMapper.sigupRequestDtoToUser(request);

        userRepository.save(user);
        return "User Registered Successfully!";
    }

    @Override
    public String login(LoginDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(UserNotFoundException.USER_NOT_FOUND));
            //simple password check
            if (user.getPassword().equals(request.getPassword()))
            {
                return "Login Successfully!";
            }
            else {
                return "Incorrect Password!";
            }

    }

    @Override
    public List<GetAllUserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return GetAllUserMapper.toResponseList(userList);
    }




}
