package com.example.My_take_out;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


@SpringBootTest
class MyTakeOutApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set(1, 2, 5, TimeUnit.MINUTES);
        redisTemplate.delete(1);
        System.out.println(redisTemplate.opsForValue().get(1));
    }

}
