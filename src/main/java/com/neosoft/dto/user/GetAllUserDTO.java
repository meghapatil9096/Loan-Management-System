package com.neosoft.dto.user;

import com.neosoft.entity.User;
import lombok.Data;

@Data
public class GetAllUserDTO {

    private Long id;

    private String name;

    private String email;

    private User.Role role;
}
