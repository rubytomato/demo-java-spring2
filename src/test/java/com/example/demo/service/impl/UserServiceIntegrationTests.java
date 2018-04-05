package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserOrder;
import com.example.demo.service.UserService;
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
public class UserServiceIntegrationTests {

    @Autowired
    private UserService sut;

    @Transactional(readOnly = true)
    @Test
    public void findById() {
        User actual = sut.findById(1L).orElse(null);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getUserOrders()).hasSize(27);
    }

    @Transactional(readOnly = true)
    @Test
    public void findByIdNotExists() {
        User actual = sut.findById(-1L).orElse(null);

        assertThat(actual).isNull();
    }

    @Transactional(readOnly = true)
    @Test
    public void findByGender() {
        List<User> actual = sut.findByGender(Gender.M);

        assertThat(actual).hasSize(100);
    }

    @Transactional(readOnly = true)
    @Test
    public void findUserOrders() {
        List<UserOrder> actual = sut.findUserOrders(1L);

        assertThat(actual).hasSize(27);
    }

}
