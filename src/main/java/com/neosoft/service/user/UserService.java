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

//    Get All User (Admin) paging & sorting
    List<GetAllUserDTO> getAllUsers(int pageNo, int pageSize, String sortBy,String sortDir);

//    update user for customer
    User updateUser(Long id, UpdateUserDTO updateDTO);

//    delete user by id
    void deleteUser(Long id);
}
