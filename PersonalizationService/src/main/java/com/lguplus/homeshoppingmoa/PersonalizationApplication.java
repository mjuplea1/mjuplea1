package com.lguplus.homeshoppingmoa;

import com.lguplus.homeshoppingmoa.personalization.event.CustomProcess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SuppressWarnings("deprecation")
@EnableFeignClients
@EnableBinding(CustomProcess.class)
@SpringBootApplication
public class PersonalizationApplication extends Application {

    public static void main(String[] args) {
        SpringApplication.run(PersonalizationApplication.class, args);
    }

}
