package com.neosoft.controller.user;

import com.neosoft.dto.user.*;
import com.neosoft.entity.User;
import com.neosoft.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDTO request){
        String token = userService.login(request);
        return ResponseEntity.ok(new AuthResponse(token,"Login successful"));
    }

//  get all user with paging And Sorting
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/get/all")
    ResponseEntity<List<GetAllUserDTO>> getAllUsers(@RequestParam(defaultValue = "0") int pageNo,
                                                    @RequestParam(defaultValue = "3") int pageSize,
                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                    @RequestParam(defaultValue = "asc") String sortDir){
       List<GetAllUserDTO> users = userService.getAllUsers(pageNo,pageSize,sortBy,sortDir);
       return ResponseEntity.ok(users);
    }

//    update user
    @PutMapping("/update/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO updateDTO){
        return ResponseEntity.ok(userService.updateUser(id,updateDTO));
    }

//    delete user with id
    @DeleteMapping("delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User With Id "+id+" deleted Successfully.");
    }

}
