package com.gyumani.blog.repository;

import com.gyumani.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}
//    User findByUsernameAndPassword(String username, String password);