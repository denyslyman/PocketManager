package com.pocketmanager.repository;

import com.pocketmanager.entity.CustomUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {

    CustomUser findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

}
