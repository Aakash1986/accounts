package com.nordea.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableAutoConfiguration
@ComponentScan("com.nordea.account")
@SpringBootApplication
public class AccountApplication {

    public static void main(String[] args) {
        System.setProperty("server.context-path","/nordea");
        SpringApplication.run(AccountApplication.class, args);
    }

}
