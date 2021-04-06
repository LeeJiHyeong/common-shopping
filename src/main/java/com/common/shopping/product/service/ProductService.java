package com.common.shopping.product.service;

import com.common.shopping.product.domain.Category;
import com.common.shopping.product.domain.CategoryRepository;
import com.common.shopping.product.domain.Product;
import com.common.shopping.product.domain.ProductRepository;
import com.common.shopping.product.dto.ProductDetailResponseDto;
import com.common.shopping.product.dto.ProductDto;
import com.common.shopping.product.dto.ProductRegisterRequestDto;
import com.common.shopping.product.dto.ProductUpdateRequestDto;
import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveProduct(ProductRegisterRequestDto productRegisterRequestDto) {
        //TODO:: 유저 정보 긁어오기
        Optional<User> user = this.userRepository.findByEmail(productRegisterRequestDto.getEmail());

        //TODO:: 카테고리 이름 긁어오기
        Optional<Category> category = this.categoryRepository.findByName(productRegisterRequestDto.getCategoryName());

        if (!user.isPresent() || !category.isPresent())
            throw new IllegalStateException("올바르지 않은 등록입니다.");

        return this.productRepository.save(productRegisterRequestDto.toEntity(category.get(), user.get())).getId();
    }

    @Transactional
    public ProductDetailResponseDto findByProductId(Long id) {
        Optional<Product> item = this.productRepository.findById(id);

        if (!item.isPresent()) {
            throw new IllegalStateException("요청하신 제품을 찾을 수 없습니다.");
        }

        ProductDetailResponseDto productDetailResponseDto = ProductDetailResponseDto.builder()
                .categoryName(item.get().getCategory().getName())
                .name(item.get().getName())
                .price(item.get().getPrice())
                .seller(item.get().getSeller().getEmail())
                .build();

        return productDetailResponseDto;
    }

    @Transactional
    public Long updateProduct(ProductUpdateRequestDto productUpdateRequestDto, Long id) {
        Optional<Category> updateCategory = this.categoryRepository.findByName(productUpdateRequestDto.getCategoryName());
        Optional<Product> item = this.productRepository.findById(id);

        if (!updateCategory.isPresent() || !item.isPresent()) {
            throw new IllegalStateException("올바르지 않은 수정입니다.");
        }

        item.get().update(productUpdateRequestDto.getName(), updateCategory.get(), productUpdateRequestDto.getPrice());

        return item.get().getId();
    }
}
