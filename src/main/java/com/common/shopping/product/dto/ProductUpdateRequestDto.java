package com.common.shopping.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    private String name;
    private String categoryName;
    private int price;

    @Builder
    public ProductUpdateRequestDto(String name, String categoryName, int price) {
        this.name = name;
        this.categoryName = categoryName;
        this.price = price;
    }
}
