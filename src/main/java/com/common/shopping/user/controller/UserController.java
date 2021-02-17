package com.common.shopping.user.controller;

import com.common.shopping.user.domain.User;
import com.common.shopping.user.dto.UserRegisterDto;
import com.common.shopping.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/login/doRegister")
    public User doRegisterUser(@RequestBody UserRegisterDto userRegisterDto) {
        return this.userService.registerUser(userRegisterDto);
    }
}
