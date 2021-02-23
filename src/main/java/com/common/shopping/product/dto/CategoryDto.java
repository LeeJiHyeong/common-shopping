package com.common.shopping.product.dto;

import com.common.shopping.product.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private String name;

    @Builder
    public CategoryDto(String name) {
        this.name = name;
    }

    public Category toEntity() {
        return Category.builder()
                .name(this.name)
                .build();
    }
}
