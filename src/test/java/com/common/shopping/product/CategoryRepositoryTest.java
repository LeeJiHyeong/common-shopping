package com.common.shopping.product;

import com.common.shopping.product.domain.Category;
import com.common.shopping.product.domain.CategoryRepository;
import com.common.shopping.product.dto.CategoryDto;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    public void tearDown() throws Exception {
        this.categoryRepository.deleteAll();
    }

    @Test
    public void 카테고리_등록() {
        //given
        CategoryDto categoryDto = CategoryDto.builder()
                .name("카테고리1")
                .build();

        //when
        this.categoryRepository.save(categoryDto.toEntity());
        List<Category> result = this.categoryRepository.findAll();

        //then
        Assertions.assertThat(result.get(0).getName()).isEqualTo("카테고리1");
    }
}
