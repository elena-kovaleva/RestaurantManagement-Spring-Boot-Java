package com.example.RestaurantManagement.Controllers;

import com.example.RestaurantManagement.Models.Tables;
import com.example.RestaurantManagement.Services.TableBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
public class TableBookingController {

    private final TableBookingService tableBookingService;

    @Autowired
    public TableBookingController(TableBookingService tableBookingService) {
        this.tableBookingService = tableBookingService;
    }

    @GetMapping("/book-table")
    public String bookingForm(Model model) {
        List<Tables> allTables = tableBookingService.getAllTables();
        model.addAttribute("tables", allTables);
        return "book-table";
    }

    @PostMapping("/book-table")
    public String bookTable(@RequestParam("table_id") int tableId,
                            @RequestParam("date") Date date,
                            @RequestParam("time") Time time,
                            @RequestParam("info") String info,
                            Model model) {
        String message = tableBookingService.bookTable(tableId, date, time, info);
        model.addAttribute("message", message);
        return "book-table";
    }
}
