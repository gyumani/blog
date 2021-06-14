package com.gyumani.blog.test;

import com.gyumani.blog.model.RoleType;
import com.gyumani.blog.model.User;
import com.gyumani.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{

        }catch (IllegalArgumentException e){
            return "Delete Fail: Caused by not Exist 'id' in DataBase";
        }
        userRepository.deleteById(id);
        return "삭제 되었습니다. id: "+id;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("id: "+id);
        System.out.println("password: "+requestUser.getPassword());
        System.out.println("email: "+requestUser.getEmail());

        User user=userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        //userRepository.save(user);

        return null;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        List<User> users= userRepository.findAll(pageable).getContent();
        return users;
    }


    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user=userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 존재하지 않습니다. id:" +id);
            }
        });
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id: "+user.getId());
        System.out.println("username: "+user.getUsername());
        System.out.println("password: "+user.getPassword());
        System.out.println("email: "+user.getEmail());
        System.out.println("role: "+user.getRole());
        System.out.println("createDate: "+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

}
