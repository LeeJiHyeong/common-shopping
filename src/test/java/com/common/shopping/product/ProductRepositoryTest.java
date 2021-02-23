package com.common.shopping.product;

import com.common.shopping.product.domain.Product;
import com.common.shopping.product.domain.ProductRepository;
import com.common.shopping.product.dto.ProductDto;
import com.common.shopping.user.domain.Role;
import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void after() {
        this.productRepository.deleteAll();
    }

    @Test
    public void 상품등록테스트() {

        //given
        User user = this.userRepository.findAll().get(0);

        Product product = ProductDto.builder()
                .name("상품2")
                .seller(user)
                .price(20000)
                .build().toEntity();

        //when

        this.productRepository.save(product);
        //then
        Assertions.assertThat(this.productRepository.findAll().get(0).getName()).isEqualTo(product.getName());
        Assertions.assertThat(this.productRepository.findAll().get(0).getPrice()).isEqualTo(product.getPrice());
    }
}
