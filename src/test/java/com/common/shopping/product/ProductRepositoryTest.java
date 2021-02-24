package com.common.shopping.product;

import com.common.shopping.product.domain.Category;
import com.common.shopping.product.domain.CategoryRepository;
import com.common.shopping.product.domain.Product;
import com.common.shopping.product.domain.ProductRepository;
import com.common.shopping.product.dto.CategoryDto;
import com.common.shopping.product.dto.ProductDto;
import com.common.shopping.user.domain.Role;
import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import com.common.shopping.user.dto.UserRegisterRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    /*
    ProductRepository에 대한 테스트를 위해
    user id 및 제품의 카테고리를 등록하는 전처리를 시행함
     */
    @BeforeEach
    public void before() {

        // user
        this.userRepository.save(UserRegisterRequestDto.builder()
                .nickName("닉네임89")
                .email("asd@naver.com")
                .password("1234")
                .address("대전광역시")
                .phone("010-0000-0000")
                .role(Role.GUEST)
                .build().toEntity());

        // category
        CategoryDto categoryDto = CategoryDto.builder()
                .name("카테고리1")
                .build();

        this.categoryRepository.save(categoryDto.toEntity());
    }

    @AfterEach
    public void after() {
        this.productRepository.deleteAll();
        this.userRepository.deleteAll();
        this.categoryRepository.deleteAll();
    }

    @Test
    public void 상품등록테스트() {

        //given
        User user = this.userRepository.findAll().get(0);
        Category category = this.categoryRepository.findAll().get(0);

        Product product = ProductDto.builder()
                .name("상품2")
                .seller(user)
                .category(category)
                .price(20000)
                .build().toEntity();

        //when

        this.productRepository.save(product);
        //then
        Assertions.assertThat(this.productRepository.findAll().get(0).getName()).isEqualTo(product.getName());
        Assertions.assertThat(this.productRepository.findAll().get(0).getPrice()).isEqualTo(product.getPrice());
        Assertions.assertThat(this.productRepository.findAll().get(0).getCategory().getName()).isEqualTo(category.getName());
    }
}
