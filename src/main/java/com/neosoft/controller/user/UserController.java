package com.neosoft.controller.user;

import com.neosoft.dto.user.GetAllUserDTO;
import com.neosoft.dto.user.LoginDTO;
import com.neosoft.dto.user.SignupDTO;
import com.neosoft.dto.user.UpdateUserDTO;
import com.neosoft.entity.User;
import com.neosoft.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//  create
    @PostMapping("/signup")
    ResponseEntity<String> registerUser(@Valid @RequestBody SignupDTO request){
        return ResponseEntity.ok(userService.registerUser(request));
    }

//    login
    @PostMapping("/login")
    ResponseEntity<String> login(@Valid @RequestBody LoginDTO request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(request));
    }

//    get all user(admin)
    @GetMapping("/admin/get/all")
    ResponseEntity<List<GetAllUserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
