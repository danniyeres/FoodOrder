package org.example.foodorder.service;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.*;
import org.example.foodorder.repository.AuthRepository;
import org.example.foodorder.repository.CartRepository;
import org.example.foodorder.repository.FoodRepository;
import org.example.foodorder.repository.OrderRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class ClientService {

    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final AuthRepository userRepository;

    public Cart addToCart(Long foodId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        long userId = userRepository.findByUsername(username).getId();
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new RuntimeException("Food not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return newCart;
        });

        cart.addFoodToCart(food);
        cartRepository.save(cart);

        return cart;
    }

    public Cart getCart() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Long userId = userRepository.findByUsername(username).getId();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user).orElse(new Cart());
    }

    public List<FoodOrder> getOrderHistory(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Profile profile = user.getProfile();
        return profile.getFoodOrders();
    }

    public void checkout() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new RuntimeException("User is not authenticated");
        }
        Cart cart = getCart();
        if (cart.getFoods().isEmpty()) {
            throw new RuntimeException("Cart is empty. Add items before checkout.");
        }
        User user = cart.getUser();
        Profile profile = user.getProfile();
        FoodOrder order = new FoodOrder(new ArrayList<>(cart.getFoods()), cart.getTotalAmount(), profile);
        orderRepository.save(order);
        cart.clearCart();
        cartRepository.save(cart);
    }
}
