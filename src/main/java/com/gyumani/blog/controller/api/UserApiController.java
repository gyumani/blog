package com.gyumani.blog.controller.api;

import com.gyumani.blog.ResponseDto;
import com.gyumani.blog.model.RoleType;
import com.gyumani.blog.model.User;
import com.gyumani.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController: save 호출됨");
        if(user.getUsername().equals("admin")){
            user.setRole(RoleType.ADMIN);
        }else{
            user.setRole(RoleType.USER);
        }
        userService.joinMember(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
