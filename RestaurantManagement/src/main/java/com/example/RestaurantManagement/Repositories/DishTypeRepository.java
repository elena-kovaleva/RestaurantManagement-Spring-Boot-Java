package com.example.RestaurantManagement.Repositories;

import com.example.RestaurantManagement.Models.Dish;
import com.example.RestaurantManagement.Models.DishType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishTypeRepository extends JpaRepository<DishType, Integer> {
}
