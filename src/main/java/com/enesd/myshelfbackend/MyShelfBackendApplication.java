package com.enesd.myshelfbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
@EnableScheduling
public class MyShelfBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShelfBackendApplication.class, args);
    }

}
