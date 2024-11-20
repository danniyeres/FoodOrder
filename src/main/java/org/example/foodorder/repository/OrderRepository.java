package org.example.foodorder.repository;

import org.example.foodorder.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<FoodOrder,Long> {
}
