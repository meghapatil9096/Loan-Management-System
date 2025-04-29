package com.neosoft.service.user;

import com.neosoft.dto.user.GetAllUserDTO;
import com.neosoft.dto.user.LoginDTO;
import com.neosoft.dto.user.SignupDTO;
import com.neosoft.dto.user.UpdateUserDTO;
import com.neosoft.entity.User;

import java.util.List;

public interface UserService {

//    SignUp(Create) Method
    String registerUser(SignupDTO signupDTO);

//    Login Method
    String login(LoginDTO request);

//    Get All User (Admin)
    List<GetAllUserDTO> getAllUsers();


}
