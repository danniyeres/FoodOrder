package org.example.foodorder.controller;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.User;
import org.example.foodorder.service.AdminService;
import org.example.foodorder.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final AuthService authService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.getUsers();
    }

    @GetMapping("/deleteUserById/{userId}")
    public void deleteUserById(@PathVariable Long userId){
        authService.deleteUserById(userId);
    }
}
