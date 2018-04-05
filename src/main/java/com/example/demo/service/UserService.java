package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserOrder;
import com.example.demo.type.Gender;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    List<User> findByGender(Gender gender);
    List<UserOrder> findUserOrders(Long id);
}
