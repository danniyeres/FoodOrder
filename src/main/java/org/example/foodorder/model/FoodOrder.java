package org.example.foodorder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



@Entity
@Getter
@Setter
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "food_order_id", unique = false)
    private List<Food> foods = new ArrayList<>();

    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    @JsonIgnore
    private Profile profile;

    public FoodOrder() {}

    public FoodOrder(List<Food> foods, double totalAmount, Profile profile) {
        this.foods = foods;
        this.totalAmount = totalAmount;
        this.profile = profile;
    }
}
