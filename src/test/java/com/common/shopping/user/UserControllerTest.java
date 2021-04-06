package com.common.shopping.user;

import com.common.shopping.user.domain.Role;
import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import com.common.shopping.user.dto.UserRegisterRequestDto;
import com.common.shopping.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @AfterEach
    public void tearDown() throws Exception {
        this.userRepository.deleteAll();
    }

    @Test
    public void 회원가입() throws Exception {
        // given
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .nickName("닉네임99")
                .email("asdf@naver.com")
                .password("123456")
                .address("대전광역시")
                .phone("010-0000-0000")
                .role(Role.ADMIN)
                .build();

        String url = "http://localhost:" + this.port + "/login/doRegister";

        // when
        ResponseEntity<User> responseEntity = this.restTemplate.postForEntity(url, userRegisterRequestDto, User.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getNickName()).isEqualTo(userRegisterRequestDto.getNickName());

        List<User> all = this.userRepository.findAll();
        Assertions.assertThat(all.get(0).getNickName()).isEqualTo(userRegisterRequestDto.getNickName());

    }



}
