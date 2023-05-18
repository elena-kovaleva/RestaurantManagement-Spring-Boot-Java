package com.example.RestaurantManagement.Controllers;

import com.example.RestaurantManagement.Models.Dish;
import com.example.RestaurantManagement.Models.OrderedDish;
import com.example.RestaurantManagement.Repositories.DishRepository;
import com.example.RestaurantManagement.Repositories.OrderedDishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class KitchenController {
    private final OrderedDishRepository orderedDishRepository;
    private final DishRepository dishRepository;

    public KitchenController(OrderedDishRepository orderedDishRepository, DishRepository dishRepository) {
        this.orderedDishRepository = orderedDishRepository;
        this.dishRepository = dishRepository;
    }

    @GetMapping("/kitchen")
    public String getKitchenOrders(Model model) {
        List<OrderedDish> orderedDishes = orderedDishRepository.findAll();
        model.addAttribute("orderedDishes", orderedDishes);
        return "kitchen";
    }

    @PostMapping("/update-dish-status/{id}")
    public String updateDishStatus(@PathVariable("id") int id, String status) {
        OrderedDish orderedDish = orderedDishRepository.getOne(id);
        orderedDish.setStatus(status);
        orderedDishRepository.save(orderedDish);
        return "redirect:/kitchen";
    }

    @GetMapping("/recipe/{id}")
    public String getDishRecipe(@PathVariable("id") int id, Model model) {
        Dish dish = dishRepository.getOne(id);
        model.addAttribute("dish", dish);
        return "recipe";
    }

}
