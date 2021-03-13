package com.common.shopping.product.controller;

import com.common.shopping.product.dto.ProductDto;
import com.common.shopping.product.dto.ProductRegisterRequestDto;
import com.common.shopping.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/register")
    public Long doRegisterProduct(@RequestBody @Valid ProductRegisterRequestDto productRegisterRequestDto) {
        return this.productService.saveProduct(productRegisterRequestDto);
    }

    @PutMapping(value = "/modify/{productId}")
    public ProductDto doModifyProduct(@RequestBody @Valid ProductDto productDto,
                                      @PathVariable("productId") Long productId) {

        // pid 조회 후 수정해서 save
        return null;
    }
}
