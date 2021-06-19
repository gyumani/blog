package com.gyumani.blog.service;

import com.gyumani.blog.model.RoleType;
import com.gyumani.blog.model.User;
import com.gyumani.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    @Transactional(readOnly = true)
    public User findMember(String username){
        User user=userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return user;

    }

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
        
        //Vaildate 체크 =>oauth 필드에 값이 없으면 수정가능
        if(persistance.getOauth()==null || persistance.getOauth().equals("")){
            String rawPassword=user.getPassword();
            String encPassword= encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }
      

    }

}

