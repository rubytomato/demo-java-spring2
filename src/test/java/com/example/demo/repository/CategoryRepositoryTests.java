package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CategoryRepository sut;

    @Test
    @Sql(statements = "INSERT INTO category(id, name, create_at, update_at) VALUES (1, 'test category', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    public void findById() {
        Category expected = testEntityManager.find(Category.class, 1L);
        Optional<Category> actual = sut.findById(1L);

        assertThat(actual.get()).isEqualTo(expected);
    }

    @Test
    @Sql(statements = {
            "INSERT INTO category(id, name, create_at, update_at) VALUES (1, 'test HOME category', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            "INSERT INTO category(id, name, create_at, update_at) VALUES (2, 'test HOME category', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)"
    })
    public void findByNameLike() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Category> actual = sut.findByNameLike("%HOME%", pageable);

        assertThat(actual).hasSize(2);
    }
}
