package com.common.shopping.user.service;

import com.common.shopping.user.domain.User;
import com.common.shopping.user.domain.UserRepository;
import com.common.shopping.user.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserRegisterDto userRegisterDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRegisterDto.setPassword(encoder.encode(userRegisterDto.getPassword()));

        return this.userRepository.save(userRegisterDto.toEntity());
    }
}
