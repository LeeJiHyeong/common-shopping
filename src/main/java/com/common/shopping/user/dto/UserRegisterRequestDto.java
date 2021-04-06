package com.common.shopping.user.dto;

import com.common.shopping.user.domain.Role;
import com.common.shopping.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequestDto {

    private Long id;
    private String nickName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Role role;

    @Builder
    public UserRegisterRequestDto(String nickName, String email, String password,
                                  String phone, String address, Role role) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .nickName(this.nickName)
                .email(this.email)
                .password(this.password)
                .phone(this.phone)
                .role(Role.GUEST)
                .address(this.address)
                .build();
    }
}
