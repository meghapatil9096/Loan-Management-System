package com.neosoft.service.user;

import com.neosoft.dto.user.GetAllUserDTO;
import com.neosoft.dto.user.LoginDTO;
import com.neosoft.dto.user.SignupDTO;
import com.neosoft.dto.user.UpdateUserDTO;
import com.neosoft.entity.User;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.mapper.user.GetAllUserMapper;
import com.neosoft.mapper.user.SignupMapper;
import com.neosoft.mapper.user.UpdateMapper;
import com.neosoft.repository.UserRepository;
import com.neosoft.security.jwt.JwtTokenProvider;
import com.neosoft.security.service.CustomUserDetailsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

//    @Autowired
    private final  UserRepository userRepository;

    private final SignupMapper mapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;


//    signup user
    @Override
    public String registerUser(SignupDTO request) {
//  check if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new RuntimeException("Email already register");
        }

        User user = mapper.sigupRequestDtoToUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "User Registered Successfully!";
    }

//    login user
    @Override
    public String login(LoginDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        return jwtTokenProvider.generateToken(userDetails);
    }

//    get all user with paging And Sorting
    @Override
    public List<GetAllUserDTO> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc")?Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize,Sort.by(direction,sortBy));
        Page<User> userPage = userRepository.findAll(pageRequest);
        return userPage
                .getContent()
                .stream()
                .map(GetAllUserMapper::toResponse)
                .toList();
    }

//  update user
    @Override
    public User updateUser(Long id, UpdateUserDTO updateDTO) {

        // Get the currently authenticated user's email
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = auth.getName();  //this is email

        // find the Logged-in user from the repository
        User loggedInUser = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() -> new RuntimeException("Logged in user not found"));

       // check if the logged-in user is trying to update their own data
        if (!loggedInUser.getId().equals(id)){
            throw new AccessDeniedException("You are not allowed to update other user's data.");
        }

       // find the user to update
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id:"+ id));
       // update field using mapper
        UpdateMapper.updateUserFromDTO(updateDTO, existingUser);
//      Encrypt password if updatedDTO contain password and it's different
        if(updateDTO.getPassword() != null && !updateDTO.getPassword().isBlank()){
           String encodedPassword = passwordEncoder.encode(updateDTO.getPassword());
           existingUser.setPassword(encodedPassword);
        }

       // save and return the updated user
        return userRepository.save(existingUser);

    }

//    delete user
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException("User with ID "+id+" Not Found.");
        }
        userRepository.deleteUserById(id);
    }

}
