package com.example.real_store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.real_store.mapper")
@SpringBootApplication
public class RealStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealStoreApplication.class, args);
    }

}
