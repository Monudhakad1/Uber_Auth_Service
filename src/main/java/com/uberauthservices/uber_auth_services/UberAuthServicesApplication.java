package com.uberauthservices.uber_auth_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UberAuthServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberAuthServicesApplication.class, args);
    }

}
