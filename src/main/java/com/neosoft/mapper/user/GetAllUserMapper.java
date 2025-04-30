package com.neosoft.mapper.user;

import com.neosoft.dto.user.GetAllUserDTO;
import com.neosoft.entity.User;
import org.springframework.stereotype.Component;

@Component
public class GetAllUserMapper {

    public  static GetAllUserDTO toResponse(User user)
    {
        GetAllUserDTO dto = new GetAllUserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

}
