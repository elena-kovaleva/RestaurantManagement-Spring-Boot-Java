package com.example.RestaurantManagement.Controllers;

import com.example.RestaurantManagement.Models.OrderedDish;
import com.example.RestaurantManagement.Repositories.OrderedDishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {
    private final OrderedDishRepository orderedDishRepository;

    public OrderController(OrderedDishRepository orderedDishRepository) {
        this.orderedDishRepository = orderedDishRepository;
    }

    @GetMapping("/manage-orders")
    public String manageOrders(Model model) {
        List<OrderedDish> orderedDishes = orderedDishRepository.findAll();
        model.addAttribute("orderedDishes", orderedDishes);
        return "manage-orders";
    }

    @GetMapping("/create-order")
    public String createOrder() {
        return "createOrder";
    }

    @GetMapping("/view-orders")
    public String viewOrders() {
        return "viewOrders";
    }
}
