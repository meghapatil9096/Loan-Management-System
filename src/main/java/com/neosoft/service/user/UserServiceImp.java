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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

//    @Autowired
    private final  UserRepository userRepository;

//    @Autowired
    private final SignupMapper mapper;

    private final PasswordEncoder passwordEncoder;

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

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(UserNotFoundException.USER_NOT_FOUND));
            //simple password check
            if (!passwordEncoder.matches(request.getPassword(),user.getPassword()))
            {
                throw new IllegalArgumentException("Invalid credential : Incorrect Password!");
            }
                return "Login Successfully!";

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

        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id:"+ id));
        UpdateMapper.updateUserFromDTO(updateDTO, existingUser);
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException("User with ID "+id+" Not Found.");
        }
        userRepository.deleteUserById(id);
    }

}
