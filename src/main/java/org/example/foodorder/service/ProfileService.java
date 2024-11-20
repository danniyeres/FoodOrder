package org.example.foodorder.service;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.Profile;
import org.example.foodorder.repository.AuthRepository;
import org.example.foodorder.repository.ProfileRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AuthRepository authRepository;


    public Profile updateProfile(Profile updatedProfile) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("User is not authenticated");
        }
        Long userId = authRepository.findByUsername(username).getId();
        Profile profile = profileRepository.findByUserId(userId);
        if (profile == null) {
            throw new RuntimeException("Profile not found for user ID: " + userId);
        }
        profile.setName(updatedProfile.getName());
        profile.setAddress(updatedProfile.getAddress());
        profile.setPhoneNumber(updatedProfile.getPhoneNumber());
        profile.setPaymentMethod(updatedProfile.getPaymentMethod());
        return profileRepository.save(profile);
    }

    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }
    public Profile getProfile(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("User is not authenticated");
        }
        Long userId = authRepository.findByUsername(username).getId();
        return profileRepository.findByUserId(userId);
    }

    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found for ID: " + id);
        }
        profileRepository.deleteById(id);
    }
}
