package com.common.shopping.product.dto;

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

    private Long id;
    private String name;
    private User seller;
    private int price;

    @Builder
    public ProductDto(String name, User seller, int price) {
        this.name = name;
        this.seller = seller;
        this.price = price;
    }

    public Product toEntity() {
        return Product.builder()
                .name(this.name)
                .seller(this.seller)
                .price(this.price)
                .build();
    }
}
