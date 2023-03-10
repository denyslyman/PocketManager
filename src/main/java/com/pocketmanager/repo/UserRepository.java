package com.pocketmanager.repo;

import com.pocketmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);


    User findByResetPasswordToken(String token);
}
