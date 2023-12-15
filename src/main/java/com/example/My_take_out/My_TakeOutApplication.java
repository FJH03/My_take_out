package com.example.My_take_out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class My_TakeOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(My_TakeOutApplication.class, args);
        log.info("项目启动成功");
    }

}
