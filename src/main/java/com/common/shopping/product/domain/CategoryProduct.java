package com.common.shopping.product.domain;

import javax.persistence.*;

@Entity
@Table(name = "category_product")
public class CategoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private Long category;

    @Column(name = "product")
    private Long product;
}
