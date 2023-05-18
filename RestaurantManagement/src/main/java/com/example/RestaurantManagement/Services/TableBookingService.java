package com.example.RestaurantManagement.Services;

import com.example.RestaurantManagement.Models.TableBooking;
import com.example.RestaurantManagement.Models.Tables;
import com.example.RestaurantManagement.Repositories.TableBookingRepository;
import com.example.RestaurantManagement.Repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class TableBookingService {

    private final TableBookingRepository tableBookingRepository;
    private final TableRepository tableRepository;

    @Autowired
    public TableBookingService(TableBookingRepository tableBookingRepository, TableRepository tableRepository) {
        this.tableBookingRepository = tableBookingRepository;
        this.tableRepository = tableRepository;
    }

    public List<Tables> getAllTables() {
        return tableRepository.findAll();
    }

    public String bookTable(int tableId, Date date, Time time, String info) {
        Tables table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid table Id:" + tableId));

        List<TableBooking> existingBookings = tableBookingRepository.findByTableAndDateAndTime(table, date, time);
        if (!existingBookings.isEmpty()) {
            return "Столик уже забронирован на выбранное время и дату.";
        }

        TableBooking newBooking = new TableBooking();
        newBooking.setTable(table);
        newBooking.setDate(date);
        newBooking.setTime(time);
        newBooking.setInfo(info);

        tableBookingRepository.save(newBooking);

        return "Столик успешно забронирован!";
    }
}
