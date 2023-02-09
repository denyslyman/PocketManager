package com.pocketmanager.service;

import com.pocketmanager.dto.UserDto;
import com.pocketmanager.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
