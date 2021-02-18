package com.common.shopping.user.service;

import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import com.common.shopping.user.dto.UserRegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRegisterRequestDto.setPassword(encoder.encode(userRegisterRequestDto.getPassword()));

        return this.userRepository.save(userRegisterRequestDto.toEntity());
    }
}
