package com.example.demo.repository;

import com.example.demo.entity.UserOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserOrderRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserOrderRepository sut;

    @Test
    @Sql(statements = {
            "INSERT INTO user (id, nick_name, gender, prefecture_id, email, create_at, update_at) VALUES (1, 'test user', 0, 0, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            "INSERT INTO category (id, name, create_at, update_at) VALUES (1, 'test category', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            "INSERT INTO item (id, name, price, sales_from, sales_to, standard_type, category_id, create_at, update_at) VALUES (1, 'test item', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            "INSERT INTO user_order (id, order_num, order_at, order_type, shipped_at, cancel_flag, item_id, user_id, create_at, update_at) VALUES (1, 1, CURRENT_TIME, 0, null, 0, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)"
    })
    public void findById() {
        UserOrder expected = testEntityManager.find(UserOrder.class, 1L);
        UserOrder actual = sut.findById(1L).orElse(null);

        assertThat(actual).as("actualは必ず検索できる").isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

}
