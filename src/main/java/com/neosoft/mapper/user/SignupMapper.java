package com.neosoft.mapper.user;

import com.neosoft.dto.user.SignupDTO;
import com.neosoft.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SignupMapper {

    public User sigupRequestDtoToUser(SignupDTO dto){
//        Mapping
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return user;
    }



}
