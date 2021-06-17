package com.gyumani.blog.service;

import com.gyumani.blog.model.RoleType;
import com.gyumani.blog.model.User;
import com.gyumani.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional
    public void joinMember(User user){
        String rawPassword=user.getPassword();
        String encPassword=encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void updateMember( User user){
        User persistance=userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("해당 회원을 찾을 수 없습니다.");
        });
        String rawPassword=user.getPassword();
        String encPassword= encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());


    }

}

