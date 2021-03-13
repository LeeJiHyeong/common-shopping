package com.common.shopping.product.service;

import com.common.shopping.product.domain.Category;
import com.common.shopping.product.domain.CategoryRepository;
import com.common.shopping.product.domain.Product;
import com.common.shopping.product.domain.ProductRepository;
import com.common.shopping.product.dto.ProductRegisterRequestDto;
import com.common.shopping.user.domain.Role;
import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void 테스트_데이터_삽입() {
        // 카테고리와 유저 테스트 데이터 필요
        Category category = Category.builder()
                .name("테스트 카테고리")
                .build();

        this.categoryRepository.save(category);

        User user = User.builder()
                .email("test@gmail.com")
                .role(Role.USER)
                .nickName("테스터")
                .phone("010-0000-0000")
                .password("testPassword")
                .address("대전광역시")
                .build();

        this.userRepository.save(user);
    }

    @AfterEach
    public void 테이블_비우기() throws Exception{
        // category, user, product 테이블 비우기
        this.productRepository.deleteAll(); // 외래키 제약조건에 의해 product 테이블부터 삭제
        this.categoryRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    public void 상품_등록_테스트() {
        //given
        String givenEmail = this.userRepository.findAll().get(0).getEmail();
        String givenCategoryName = this.categoryRepository.findAll().get(0).getName();

        ProductRegisterRequestDto productRegisterRequestDto = ProductRegisterRequestDto
                .builder()
                .categoryName(givenCategoryName)
                .email(givenEmail)
                .name("테스트 등록 상품")
                .price(30000)
                .build();

        //when
        Long id = this.productService.saveProduct(productRegisterRequestDto);

        //then
        Product foundedProduct = this.productRepository.findById(id).get();
        Assertions.assertThat(productRegisterRequestDto.getName()).isEqualTo(foundedProduct.getName());
    }
}
