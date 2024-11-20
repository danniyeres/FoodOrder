package org.example.foodorder.service;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.Profile;
import org.example.foodorder.model.Role;
import org.example.foodorder.model.User;
import org.example.foodorder.repository.AuthRepository;
import org.example.foodorder.repository.ProfileRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthService{
    private final AuthRepository authRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public User addUser(User user) {
        if(authRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()){
            user.setRoles(Collections.singleton(Role.ROLE_USER));
        }
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setName(user.getUsername());
        user.setProfile(profile);
        return authRepository.save(user);
    }

    public void changeUsername(String newUsername){
        if (newUsername == null || newUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("New username cannot be blank");
        }
        if (authRepository.existsByUsername(newUsername)) {
            throw new RuntimeException("Username already exists");
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("User is not authenticated");
        }

        User user = authRepository.findByUsername(username);
        user.setUsername(newUsername);
        authRepository.save(user);
    }

    public void changePassword(String newPassword){
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("New password cannot be blank");
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("User is not authenticated");
        }

        User user = authRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        authRepository.save(user);

    }

    public void deleteUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("User is not authenticated");
        }
        Long userId = authRepository.findByUsername(username).getId();
        authRepository.deleteById(userId);
        profileRepository.deleteById(userId);
    }

    public void deleteUserById(Long userId){
        authRepository.deleteById(userId);
    }
}
