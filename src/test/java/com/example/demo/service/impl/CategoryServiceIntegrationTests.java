package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 結合テスト
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceIntegrationTests {

    @Autowired
    private CategoryService sut;

    @Transactional(readOnly = true)
    @Test
    public void findById() {
        Category actual = sut.findById(1L).orElse(null);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1L);
    }

    @Transactional(readOnly = true)
    @Test
    public void findLikeName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Category> actual = sut.findLikeName("%HOME%", pageable);

        assertThat(actual.getContent()).hasSize(2);
    }
}
