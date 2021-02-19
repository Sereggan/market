package com.nikolaychuks.articleinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ArticleInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleInventoryApplication.class, args);
    }

}
