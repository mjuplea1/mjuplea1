package com.lguplus.homeshoppingmoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PersonalizationApplication extends Application {

    public static void main(String[] args) {
        SpringApplication.run(PersonalizationApplication.class, args);
    }

}
