package com.neosoft.mapper.user;

import com.neosoft.dto.user.GetAllUserDTO;
import com.neosoft.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
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

    public  static List<GetAllUserDTO> toResponseList(List<User> users){
        return  users.stream()
                .map(GetAllUserMapper::toResponse)
                .collect(Collectors.toList());
    }
}
