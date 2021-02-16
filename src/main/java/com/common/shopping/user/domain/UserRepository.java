package com.common.shopping.user.domain;

import com.common.shopping.user.dto.UserRegisterDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//      User save(UserRegisterDto userRegisterDto);
//    Optional<User> findByEmail(String email);
}
