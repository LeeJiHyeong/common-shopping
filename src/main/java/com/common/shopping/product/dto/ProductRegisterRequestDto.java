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
public class ProductRegisterRequestDto {

    private String name;
    private String email;
    private int price;
    private String categoryName;

    @Builder
    public ProductRegisterRequestDto(String name, String email, int price, String categoryName) {
        this.name = name;
        this.email = email;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Product toEntity(Category category, User seller) {
        return Product.builder()
                .category(category)
                .seller(seller)
                .price(this.price)
                .name(this.name)
                .build();
    }
}
