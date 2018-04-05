package com.example.demo.repository;

import com.example.demo.entity.Item;
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
public class ItemRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ItemRepository sut;

    @Test
    @Sql(statements = {
            "INSERT INTO category(id, name, create_at, update_at) VALUES (1, 'test category', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            "INSERT INTO item(id, name, price, sales_from, sales_to, standard_type, category_id, create_at, update_at) VALUES (11, 'test item', 100, CURRENT_DATE, CURRENT_DATE, 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)"
    })
    public void findById() {
        Item expected = testEntityManager.find(Item.class, 11L);
        Item actual = sut.findById(11L).orElse(null);

        assertThat(actual).as("actualは必ず検索できる").isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

}
