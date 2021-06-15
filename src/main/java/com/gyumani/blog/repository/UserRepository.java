package com.gyumani.blog.repository;

import com.gyumani.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsernameAndPassword(String username, String password);
}
