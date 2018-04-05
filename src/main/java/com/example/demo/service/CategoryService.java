package com.example.demo.service;

import com.example.demo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);
    Page<Category> findAll(Pageable pageable);
    Page<Category> findLikeName(String name, Pageable pageable);
}
