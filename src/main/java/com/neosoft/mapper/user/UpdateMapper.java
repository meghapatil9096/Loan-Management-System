package com.neosoft.mapper.user;

import com.neosoft.dto.user.UpdateUserDTO;
import com.neosoft.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateMapper {

    public static void updateUserFromDTO(UpdateUserDTO dto, User user){
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
    }
}
