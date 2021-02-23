package com.common.shopping.product.domain;

import com.common.shopping.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
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

    @Column(name = "price")
    private int price;

    @Builder
    public Product(String name, User seller, int price) {
        this.name = name;
        this.seller = seller;
        this.price = price;
    }

    public void update(String name, int price) {
        this.name = name;
        this.price = price;
    }

}
