package com.neosoft.dto;

import com.neosoft.entity.User;
import lombok.Data;
@Data
public class SignupRequest {

    private int id;

    private String name;

    private String email;

    private String password;

    private User.Role role;
}
