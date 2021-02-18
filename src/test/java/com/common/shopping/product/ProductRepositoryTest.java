package com.common.shopping.product;

import com.common.shopping.product.domain.Product;
import com.common.shopping.product.domain.ProductRepository;
import com.common.shopping.product.dto.ProductDto;
import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 상품등록테스트() {

        //given
        User user = this.userRepository.findAll().get(0);

        Product product = ProductDto.builder()
                .name("상품2")
                .seller(user)
                .build().toEntity();
        //when

        //then
        this.productRepository.save(product);

    }
}
