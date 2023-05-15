package com.example.RestaurantManagement.Services;

import com.example.RestaurantManagement.Models.Staff;
import com.example.RestaurantManagement.Repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public void deleteStaff(Staff staff) {
        staffRepository.delete(staff);
    }
}
