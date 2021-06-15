package com.gyumani.blog.service;

import com.gyumani.blog.model.User;
import com.gyumani.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void joinMember(User user){
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User 로그인(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
