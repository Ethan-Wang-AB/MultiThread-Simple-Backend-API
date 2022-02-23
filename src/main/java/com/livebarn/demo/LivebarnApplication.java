package com.livebarn.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livebarn.demo.domains.Success;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class LivebarnApplication {

    @Bean
    public ObjectMapper getObjectMapper(){
    return new ObjectMapper();
    }
    
    @Bean
    public Success getSuccess(){
    return new Success(true);
    }

    
    public static void main(String[] args) {
        SpringApplication.run(LivebarnApplication.class, args);
    }

}
