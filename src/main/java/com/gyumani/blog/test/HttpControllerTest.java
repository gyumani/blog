package com.gyumani.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    private String message;

    @GetMapping("/http/get")
    public String getTest(Member m){
        message="get 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
        return message;
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        message="post 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
        return message;
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        message="put 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
        return message;
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        message="delete 요청";
        return message;
    }
}
