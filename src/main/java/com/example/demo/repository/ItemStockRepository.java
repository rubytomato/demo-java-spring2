package com.example.demo.repository;

import com.example.demo.entity.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemStockRepository extends JpaRepository<ItemStock, Long> {
}
