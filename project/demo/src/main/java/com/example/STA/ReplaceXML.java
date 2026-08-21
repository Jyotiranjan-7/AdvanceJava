package com.example.STA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplaceXML {

    @Bean
    public Address getAddress() {
        return new Address("Bhubaneswar");
    }

    @Bean
    public Employee getDetail() {
        return new Employee(101, getAddress());
    }
}
