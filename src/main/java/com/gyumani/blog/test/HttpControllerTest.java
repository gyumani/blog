package com.gyumani.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    private String message;
    private final String TAG="HttpControllerTest: ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m=Member.builder().username("jkm1227").password("12345").email("email").build();
        System.out.println(TAG+"getter: "+m.getId());
        m.setId(5000);
        System.out.println(TAG+"setter: "+m.getId());
        return "lombok test 완료";
    }

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
