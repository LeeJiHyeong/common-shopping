package com.common.shopping;

import com.common.shopping.user.domain.Role;
import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import com.common.shopping.user.dto.UserRegisterDto;
import com.common.shopping.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void 회원가입() {
        // 컨트롤러 생성 이전 초기 테스트
        this.userRepository.save(UserRegisterDto.builder()
                .nickName("닉네임1")
                .email("asd@naver.com")
                .password("1234")
                .address("대전광역시")
                .phone("010-0000-0000")
                .role(Role.GUEST)
                .build().toEntity());

        List<User> all = this.userRepository.findAll();

        Assertions.assertThat(all.get(0).getNickName()).isEqualTo("닉네임1");
    }

    @Test
    public void 회원가입_비밀번호암호화() {

        User registeredUser = this.userService.registerUser(UserRegisterDto.builder()
                .nickName("닉네임2")
                .email("asdf@naver.com")
                .password("12345")
                .address("대전광역시")
                .phone("010-0000-0000")
                .role(Role.GUEST)
                .build());

        Assertions.assertThat(registeredUser.getNickName()).isEqualTo("닉네임2");
        Assertions.assertThat(registeredUser.getPassword()).isNotEqualTo("12345");
    }
}
