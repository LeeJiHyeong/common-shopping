package com.common.shopping.product.controller;

import com.common.shopping.product.dto.ProductDetailResponseDto;
import com.common.shopping.product.dto.ProductDto;
import com.common.shopping.product.dto.ProductRegisterRequestDto;
import com.common.shopping.product.dto.ProductUpdateRequestDto;
import com.common.shopping.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    // 상품 조회
    @GetMapping(value = "/detail/{productId}")
    public ProductDetailResponseDto showProductDetail(@PathVariable("productId") Long productId) {
        return this.productService.findByProductId(productId);
    }

    // 상품 등록
    @PostMapping(value = "/register")
    public Long registerProduct(@RequestBody @Valid ProductRegisterRequestDto productRegisterRequestDto) {
        return this.productService.saveProduct(productRegisterRequestDto);
    }

    // 상품 정보 수정
    @PutMapping(value = "/update/{productId}")
    public Long updateProduct(@RequestBody @Valid ProductUpdateRequestDto productUpdateRequestDto,
                                      @PathVariable("productId") Long productId) {

        // pid 조회 후 수정해서 save
        return this.productService.updateProduct(productUpdateRequestDto, productId);
    }
}
