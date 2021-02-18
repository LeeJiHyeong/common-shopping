package com.common.shopping.product.domain;

import com.common.shopping.user.domain.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    /*
    User Entity와 양방향 연관관계를 가짐
    product table이 FK로 User를 참고하고 있음
    Product Entity를 연관관계의 주인으로 설정
     */
    @ManyToOne
    @JoinColumn(name = "seller")
    private User seller;

    @Builder
    public Product(String name, User seller) {
        this.name = name;
        this.seller = seller;
    }

    public void update(String name) {
        this.name = name;
    }

}
