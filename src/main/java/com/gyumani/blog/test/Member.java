package com.gyumani.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter(Getters 생성)
//@Setter(Setters 생성)
@Data
//@AllArgsConstructor//(일반적인 생성자 생성)
@NoArgsConstructor//(빈생성자)
//@RequiredArgsConstructor(final :상수를 이용한 생성자 생성)
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;

    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
