package com.example.RestaurantManagement.Controllers;

import com.example.RestaurantManagement.Models.Dish;
import com.example.RestaurantManagement.Models.Order;
import com.example.RestaurantManagement.Models.OrderedDish;
import com.example.RestaurantManagement.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedDishRepository orderedDishRepository;

    @Autowired
    private TablesRepository tablesRepository;

    @Autowired
    private DishTypeRepository dishTypeRepository;

    @GetMapping("/create-order")
    public String createOrderForm(Model model) {
        model.addAttribute("dishes", dishRepository.findAll());
        model.addAttribute("types", dishTypeRepository.findAll());
        model.addAttribute("tables", tablesRepository.findAll());
        return "create-order";
    }


    @PostMapping("/create-order")
    public String submitOrder(@RequestParam Integer table_id,
                              @RequestParam List<Integer> dish_ids,
                              RedirectAttributes redirectAttributes) {
        Order order = new Order();
        order.setTotalCost(calculateTotalCost(dish_ids));
        orderRepository.save(order);
        for (Integer dish_id : dish_ids) {
            Optional<Dish> dishOptional = dishRepository.findById(dish_id);
            if (dishOptional.isPresent()) {
                OrderedDish orderedDish = new OrderedDish();
                orderedDish.setDish(dishOptional.get());
                orderedDish.setOrder(order);
                orderedDishRepository.save(orderedDish);
            }
        }

        redirectAttributes.addFlashAttribute("message", "Заказ успешно создан!");
        return "redirect:/view-orders";
    }

    private double calculateTotalCost(List<Integer> dish_ids) {
        double totalCost = 0;
        for (Integer dish_id : dish_ids) {
            Optional<Dish> dishOptional = dishRepository.findById(dish_id);
            if (dishOptional.isPresent()) {
                totalCost += dishOptional.get().getCost();
            }
        }
        return totalCost;
    }
}
