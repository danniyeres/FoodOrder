package org.example.foodorder.controller;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.Profile;
import org.example.foodorder.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @PutMapping("/update")
    public Profile updateProfile(@RequestBody Profile updatedProfile) {
        return profileService.updateProfile(updatedProfile);
    }
    @GetMapping("/getProfile")
    public Profile getProfile() {
        return profileService.getProfile();
    }
    @GetMapping("/{userId}")
    public Profile getProfile(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId);
    }
}
