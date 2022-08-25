package com.dog_house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class DogHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogHouseApplication.class, args);
    }

    @Bean
    SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
    
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
