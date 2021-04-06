package com.common.shopping.product.dto;

import com.common.shopping.product.domain.Category;
import com.common.shopping.product.domain.Product;
import com.common.shopping.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private String name;
    private User seller;
    private int price;
    private Category category;

    @Builder
    public ProductDto(String name, User seller, int price, Category category) {
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.category = category;
    }

    public Product toEntity() {
        return Product.builder()
                .name(this.name)
                .seller(this.seller)
                .price(this.price)
                .category(this.category)
                .build();
    }
}
