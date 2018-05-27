package com.example.demo.repository;

import com.example.demo.entity.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReviewRepository extends JpaRepository<CustomerReview, Long> {
}
