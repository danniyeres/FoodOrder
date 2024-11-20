package org.example.foodorder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<Food> foods = new ArrayList<>();

    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void addFoodToCart(Food food) {
        this.foods.add(food);
        this.totalAmount += food.getPrice();
    }

    public void removeFoodFromCart(Food food) {
        this.foods.remove(food);
        this.totalAmount -= food.getPrice();
    }

    public void clearCart() {
        this.foods.clear();
        this.totalAmount = 0;
    }
}
