
package org.example.foodorder.controller;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.Food;
import org.example.foodorder.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foods")
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/all")
    public List<Food> getAllFoods(){
        System.out.println(foodService.getAllFoods());
        return foodService.getAllFoods();
    }
    @PostMapping("/create")
    public Food createFood(@RequestBody Food food){
        return foodService.createFood(food);
    }
    @PostMapping("/delete/{foodId}")
    public void deleteFood(@PathVariable Long foodId){
        foodService.deleteFood(foodId);
    }
}
