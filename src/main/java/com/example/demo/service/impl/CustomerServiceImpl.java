package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.CustomerReview;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository) {
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
    }

    @Transactional(readOnly = true, timeout = 3)
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional(readOnly = true, timeout = 10)
    @Override
    public List<Customer> findByGender(final Gender gender) {
        try (Stream<Customer> customers = customerRepository.streamAllBy()) {
            return customers
                    .filter(customer -> customer.getGender() == gender)
                    .limit(100)
                    .collect(Collectors.toList());
        }
    }

    @Transactional(readOnly = true, timeout = 10)
    @Override
    public List<CustomerOrder> findCustomerOrders(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("not exists customer"));
        return customerOrderRepository.findByCustomer(customer);
    }

    @Transactional(readOnly = true, timeout = 10)
    @Override
    public List<CustomerReview> findCustomerReviews(Long id) {
        return customerRepository.findById(id).map(Customer::getCustomerReviews).orElseThrow(() -> new RuntimeException("not exists customer"));
    }

    @Transactional(timeout = 3)
    @Override
    public void changeNickName(Long id, String nickName) {
        customerRepository.findById(id).ifPresent(customer -> customer.setNickName(nickName));
    }

}
