package com.common.shopping.user.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Calendar;

@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name", unique = true)
    @NotNull
    private String nickName;

    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "phone")
    @NotNull
    private String phone;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "role")
    @NotNull
    private Role role;

    @Column(name = "create_date")
    private Calendar createDate;

    @Builder
    public User(String nickName, String email, String password, String phone, String address, Role role) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
}
