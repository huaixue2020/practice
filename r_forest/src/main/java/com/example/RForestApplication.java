package com.example;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ForestScan(basePackages = "com.example.dao")
public class RForestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RForestApplication.class, args);
    }

}
