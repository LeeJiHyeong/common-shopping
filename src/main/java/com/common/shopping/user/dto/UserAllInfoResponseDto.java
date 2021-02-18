package com.common.shopping.user.dto;

import com.common.shopping.user.domain.Role;
import com.common.shopping.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAllInfoResponseDto {
    private Long id;
    private String nickName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Role role;

    public UserAllInfoResponseDto(User userEntity) {
        this.id = userEntity.getId();
        this.nickName = userEntity.getNickName();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.phone = userEntity.getPhone();
        this.address = userEntity.getAddress();
        this.role = userEntity.getRole();
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
