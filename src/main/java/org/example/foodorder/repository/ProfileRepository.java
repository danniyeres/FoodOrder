package org.example.foodorder.repository;

import org.example.foodorder.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Profile findByUserId(Long userId);

}
