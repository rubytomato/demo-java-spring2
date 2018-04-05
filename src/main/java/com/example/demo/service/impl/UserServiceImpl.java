package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserOrder;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.type.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository user;
    private final UserOrderRepository userOrder;

    public UserServiceImpl(UserRepository user, UserOrderRepository userOrder) {
        this.user = user;
        this.userOrder = userOrder;
    }

    @Transactional(readOnly = true, timeout = 3)
    @Override
    public Optional<User> findById(Long id) {
        return user.findById(id);
    }

    @Transactional(readOnly = true, timeout = 10)
    @Override
    public List<User> findByGender(final Gender gender) {
        try (Stream<User> users = user.streamAllBy()) {
            return users
                    .filter(user -> user.getGender() == gender)
                    .limit(100)
                    .collect(Collectors.toList());
        }
    }

    @Transactional(readOnly = true, timeout = 10)
    @Override
    public List<UserOrder> findUserOrders(Long id) {
        User u = user.findById(id).orElseThrow(() -> new RuntimeException("not exists user"));
        return userOrder.findByUser(u);
    }

}
