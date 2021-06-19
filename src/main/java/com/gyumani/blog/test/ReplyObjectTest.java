package com.gyumani.blog.test;

import com.gyumani.blog.model.Reply;
import org.junit.Test;

public class ReplyObjectTest {

    @Test
    public void toStringTest(){
        Reply reply=Reply.builder()
                .id(1)
                .user(null)
                .board(null)
                .content("안녕")
                .build();

        System.out.println(reply); //오브젝트 출력시에  toString가 자동출력됨
    }
}
