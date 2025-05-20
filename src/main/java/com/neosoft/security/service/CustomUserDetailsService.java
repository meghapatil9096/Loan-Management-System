package com.neosoft.security.service;

import com.neosoft.entity.User;
import com.neosoft.repository.UserRepository;
import com.neosoft.security.model.CustomerUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found with emal : "+email));
        return new CustomerUserDetails(user);
    }
}
