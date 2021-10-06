package org.arda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AmazonKeywordScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonKeywordScoreApplication.class, args);
    }

}
