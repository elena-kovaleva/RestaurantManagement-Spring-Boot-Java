package com.example.RestaurantManagement.Repositories;

import com.example.RestaurantManagement.Models.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Tables, Integer> {
}
