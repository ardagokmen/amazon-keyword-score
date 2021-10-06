package org.arda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SellicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellicsApplication.class, args);
    }

}
