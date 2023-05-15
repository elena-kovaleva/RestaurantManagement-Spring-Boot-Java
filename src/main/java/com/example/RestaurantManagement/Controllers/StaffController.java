package com.example.RestaurantManagement.Controllers;

import com.example.RestaurantManagement.Models.Staff;
import com.example.RestaurantManagement.Services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staff")
    public String getStaff(Model model) {
        model.addAttribute("staff", staffService.getAllStaff());
        model.addAttribute("newStaff", new Staff());
        return "staff";
    }

    @PostMapping("/staff/add")
    public String addStaff(@ModelAttribute Staff staff) {
        staff.setApparatusEmployed((java.sql.Date) new Date());
        staffService.saveStaff(staff);
        return "redirect:/staff";
    }

    @PostMapping("/staff/update")
    public String updateStaff(@ModelAttribute Staff staff) {
        staffService.updateStaff(staff);
        return "redirect:/staff";
    }

    @PostMapping("/staff/delete")
    public String deleteStaff(@ModelAttribute Staff staff) {
        staffService.deleteStaff(staff);
        return "redirect:/staff";
    }
}
