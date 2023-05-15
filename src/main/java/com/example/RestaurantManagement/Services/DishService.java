package com.example.RestaurantManagement.Services;

import com.example.RestaurantManagement.Models.Dish;
import com.example.RestaurantManagement.Repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
}
