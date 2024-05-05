package com.enesd.myshelfbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
@EnableElasticsearchRepositories(basePackages = "com.enesd.myshelfbackend.repository.elasticsearch")
@EnableJpaRepositories(basePackages = "com.enesd.myshelfbackend.repository.jpa")
@EnableScheduling
public class MyShelfBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShelfBackendApplication.class, args);
    }
}
