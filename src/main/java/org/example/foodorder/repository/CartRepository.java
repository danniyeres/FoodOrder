package org.example.foodorder.repository;

import org.example.foodorder.model.Cart;
import org.example.foodorder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
