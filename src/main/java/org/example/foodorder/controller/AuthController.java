package org.example.foodorder.controller;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.User;
import org.example.foodorder.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.addUser(user);
    }

    @PostMapping("/change/username")
    public void changeUsername(@RequestParam String newUsername) {
        authService.changeUsername(newUsername);
    }

    @PostMapping("/change/password")
    public void changePassword(@RequestParam String newPassword){
        authService.changePassword(newPassword);
    }

    @GetMapping("/deleteAccount")
    public void deleteAccount(){
        authService.deleteUser();
    }

}
