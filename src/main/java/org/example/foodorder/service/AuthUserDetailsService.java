package org.example.foodorder.service;

import lombok.AllArgsConstructor;
import org.example.foodorder.config.AuthUserDetails;
import org.example.foodorder.model.User;
import org.example.foodorder.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@AllArgsConstructor
@Service
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    private final AuthRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new AuthUserDetails(user);
    }
}
