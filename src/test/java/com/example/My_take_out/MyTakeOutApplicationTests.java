package com.example.My_take_out;

import com.example.My_take_out.common.MyfileMethods;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MyTakeOutApplicationTests {

    @Test
    void contextLoads() {
        MyfileMethods myfileMethods = new MyfileMethods();
        myfileMethods.backep(1L, "f9faaeb7-05ed-4425-b2cf-ca9e1e59b172.jpg");

    }

}
