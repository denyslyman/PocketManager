package com.pocketmanager.service;

import com.pocketmanager.entity.CustomUser;
import com.pocketmanager.entity.UserDto;
import com.pocketmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public CustomUser findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void saveUser(UserDto userDto){
        CustomUser customUser = new CustomUser();
        customUser.setName(userDto.getName());
        customUser.setEmail(userDto.getEmail());
        customUser.setPhone(userDto.getPhone());
        customUser.setPassword(userDto.getPassword());
        customUser.setConfirmPassword(userDto.getConfirmPassword());

        userRepository.save(customUser);


    }
    @Transactional
    public boolean loginUser(String email, String password){
        if (userRepository.existsByEmailAndPassword(email, password)){
            return true;
        }else {
            return false;
        }
    }
}
