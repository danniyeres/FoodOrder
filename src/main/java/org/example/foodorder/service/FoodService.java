
package org.example.foodorder.service;

import lombok.AllArgsConstructor;
import org.example.foodorder.model.Food;
import org.example.foodorder.repository.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;


    public Food createFood(Food food){
        return foodRepository.save(food);
    }
    public List<Food> getAllFoods(){
        return foodRepository.findAll();
    }
    public void deleteFood(Long foodId){
        foodRepository.deleteById(foodId);
    }

}
