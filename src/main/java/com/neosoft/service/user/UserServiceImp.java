package com.neosoft.service.user;

import com.neosoft.dto.user.GetAllUserDTO;
import com.neosoft.dto.user.LoginDTO;
import com.neosoft.dto.user.SignupDTO;
import com.neosoft.dto.user.UpdateUserDTO;
import com.neosoft.entity.User;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.mapper.user.GetAllUserMapper;
import com.neosoft.mapper.user.SignupMapper;
import com.neosoft.mapper.user.UpdateMapper;
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
    private SignupMapper mapper;

    @Override
    public String registerUser(SignupDTO request) {
//  check if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new RuntimeException("");
        }

        User user = mapper.sigupRequestDtoToUser(request);

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

    @Override
    public User updateUser(Long id, UpdateUserDTO updateDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id:"+ id));
        UpdateMapper.updateUserFromDTO(updateDTO, existingUser);
        return userRepository.save(existingUser);
    }


}
