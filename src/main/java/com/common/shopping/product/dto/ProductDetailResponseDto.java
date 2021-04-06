package com.common.shopping.product.dto;

import com.common.shopping.product.domain.Category;
import com.common.shopping.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailResponseDto {

    private Long id;
    private String name;
    private String seller;
    private int price;
    private String categoryName;

    @Builder
    public ProductDetailResponseDto(String name, String seller, int price, String categoryName) {
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.categoryName = categoryName;
    }
}
