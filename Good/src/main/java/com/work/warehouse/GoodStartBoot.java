package com.work.warehouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.work.warehouse.mapper")
public class GoodStartBoot {
    public static void main(String[] args) {
        SpringApplication.run(GoodStartBoot.class, args);
    }
}
