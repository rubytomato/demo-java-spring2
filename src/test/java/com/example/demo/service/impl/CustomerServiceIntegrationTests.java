package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.CustomerReview;
import com.example.demo.service.CustomerService;
import com.example.demo.type.Gender;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 結合テスト
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerService sut;

    @Transactional(readOnly = true)
    @Test
    public void findById() {
        Customer actual = sut.findById(1L).orElse(null);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getCustomerOrders()).hasSize(27);
    }

    @Transactional(readOnly = true)
    @Test
    public void findByIdNotExists() {
        Customer actual = sut.findById(-1L).orElse(null);

        assertThat(actual).isNull();
    }

    @Transactional(readOnly = true)
    @Test
    public void findByGender() {
        List<Customer> actual = sut.findByGender(Gender.M);

        assertThat(actual).hasSize(100);
    }

    @Transactional(readOnly = true)
    @Test
    public void findCustomerOrders() {
        List<CustomerOrder> actual = sut.findCustomerOrders(1L);

        assertThat(actual).hasSize(27);
    }

    @Transactional(readOnly = true)
    @Test
    public void findCustomerReviews() {
        List<CustomerReview> actual = sut.findCustomerReviews(1L);

        assertThat(actual).hasSize(27);
    }

    @Transactional
    @Test
    public void changeNickName() {
        sut.changeNickName(1L, "test nick name");

    }
}
