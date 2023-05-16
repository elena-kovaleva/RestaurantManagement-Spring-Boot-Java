package com.example.RestaurantManagement.Repositories;

import com.example.RestaurantManagement.Models.OrderedDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedDishRepository extends JpaRepository<OrderedDish, Integer> {
}

