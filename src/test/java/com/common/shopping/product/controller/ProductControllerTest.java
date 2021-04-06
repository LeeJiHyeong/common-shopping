package com.common.shopping.product.controller;

import com.common.shopping.product.domain.Category;
import com.common.shopping.product.domain.CategoryRepository;
import com.common.shopping.product.domain.Product;
import com.common.shopping.product.domain.ProductRepository;
import com.common.shopping.product.dto.ProductDetailResponseDto;
import com.common.shopping.product.dto.ProductDto;
import com.common.shopping.product.dto.ProductRegisterRequestDto;
import com.common.shopping.product.dto.ProductUpdateRequestDto;
import com.common.shopping.product.service.ProductService;
import com.common.shopping.user.domain.Role;
import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

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
    public void 테스트_데이터_삭제() throws Exception{
        // category, user, product 테이블 비우기
        this.productRepository.deleteAll(); // 외래키 제약조건에 의해 product 테이블부터 삭제
        this.categoryRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    public void 상품_등록_테스트() throws Exception {
        // given
        String url = "http://localhost:" + this.port + "/product/register";
        String givenCategoryName = this.categoryRepository.findAll().get(0).getName();
        String givenUserEmail = this.userRepository.findAll().get(0).getEmail();

        ProductRegisterRequestDto productRegisterRequestDto = ProductRegisterRequestDto.builder()
                .price(30000)
                .name("면도기")
                .email(givenUserEmail)
                .categoryName(givenCategoryName)
                .build();

        // when
        ResponseEntity<Long> responseEntity = this.testRestTemplate.postForEntity(url, productRegisterRequestDto, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Product> productList = this.productRepository.findAll();
        Assertions.assertThat(productList.get(0).getName()).isEqualTo(productRegisterRequestDto.getName());
    }

    @Test
    public void 상품_조회_테스트() {

        // 조회할 상품 테스트 데이터 삽입
        String givenEmail = this.userRepository.findAll().get(0).getEmail();
        String givenCategoryName = this.categoryRepository.findAll().get(0).getName();

        // 상품 등록
        ProductRegisterRequestDto productRegisterRequestDto = ProductRegisterRequestDto
                .builder()
                .categoryName(givenCategoryName)
                .email(givenEmail)
                .name("테스트 등록 상품")
                .price(30000)
                .build();

        Long id = this.productService.saveProduct(productRegisterRequestDto);

        String url = "http://localhost:" + this.port + "/product/detail/" + id;

        //when
        ProductDetailResponseDto item = this.testRestTemplate.getForObject(url, ProductDetailResponseDto.class);

        // then
        Assertions.assertThat(item.getName()).isEqualTo(productRegisterRequestDto.getName());
    }

    @Test
    public void 상품_업데이트_테스트() {
        // given
        // 조회할 상품 테스트 데이터 삽입
        String givenEmail = this.userRepository.findAll().get(0).getEmail();
        String givenCategoryName = this.categoryRepository.findAll().get(0).getName();

        ProductRegisterRequestDto productRegisterRequestDto = ProductRegisterRequestDto
                .builder()
                .categoryName(givenCategoryName)
                .email(givenEmail)
                .name("테스트 등록 상품")
                .price(30000)
                .build();

        // 카테고리 수정 확인을 위해 추가 카테고리 등록
        Category category = Category.builder()
                .name("수정 카테고리")
                .build();

        this.categoryRepository.save(category);

        Long id = this.productService.saveProduct(productRegisterRequestDto);
        String url = "http://localhost:" + this.port + "/product/update/" + id;

        // 수정사항
        ProductUpdateRequestDto productUpdateRequestDto = ProductUpdateRequestDto.builder()
                .categoryName("수정 카테고리")
                .name("수정한 이름")
                .price(11111)
                .build();

        HttpEntity<ProductUpdateRequestDto> requestEntity = new HttpEntity<>(productUpdateRequestDto);

        // when
        ResponseEntity<Long> responseEntity = this.testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        Product updatedItem = this.productRepository.findById(responseEntity.getBody()).get();
        Assertions.assertThat(updatedItem.getName()).isEqualTo(productUpdateRequestDto.getName());
    }

    @Test
    public void 상품_삭제_테스트() {
        // given

        // 조회할 상품 테스트 데이터 삽입
        String givenEmail = this.userRepository.findAll().get(0).getEmail();
        String givenCategoryName = this.categoryRepository.findAll().get(0).getName();

        // 상품 등록
        ProductRegisterRequestDto productRegisterRequestDto = ProductRegisterRequestDto
                .builder()
                .categoryName(givenCategoryName)
                .email(givenEmail)
                .name("테스트 등록 상품")
                .price(30000)
                .build();

        Long id = this.productService.saveProduct(productRegisterRequestDto);

        String url = "http://localhost:" + this.port + "/product/delete/" + id;

        // when
        // delete 요청 후 반환값이 필요하므로 TestRestTemplate.exchange() 사용
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Long> responseEntity = this.testRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(id).isEqualTo(responseEntity.getBody());

    }
}
