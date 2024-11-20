package org.example.foodorder.repository;


import org.example.foodorder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);
    boolean existsByUsername(String username);
}
