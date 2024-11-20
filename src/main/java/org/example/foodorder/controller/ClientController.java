package org.example.foodorder.controller;


import lombok.AllArgsConstructor;
import org.example.foodorder.model.Cart;
import org.example.foodorder.model.FoodOrder;
import org.example.foodorder.service.ClientService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/addFood/{foodId}")
    public Cart addToCart( @PathVariable Long foodId) {
        return clientService.addToCart(foodId);
    }
    @GetMapping("/cart")
    public Cart viewCart() {
        return clientService.getCart();
    }
    @GetMapping("/profile/{userId}/orders")
    public List<FoodOrder> getOrderHistory(@PathVariable Long userId) {
        return clientService.getOrderHistory(userId);
    }
    @GetMapping("/checkout")
    public void checkout() {
        clientService.checkout();
    }
}
