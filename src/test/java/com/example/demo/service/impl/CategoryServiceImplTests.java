package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * 単体テスト
 */
public class CategoryServiceImplTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @InjectMocks
    private CategoryServiceImpl sut;

    @Mock
    private CategoryRepository repository;

    @Test
    public void findById() {
        Category expected = Category.of(1L, "test category");
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(expected));

        Category actual = sut.findById(1L).orElse(null);

        assertThat(actual).as("actualは必ず検索できる").isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

}
