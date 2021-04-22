package com.elevator.ElevatorSpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ElevatorSpringBootApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(ElevatorSpringBootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(ElevatorSpringBootApplication.class);
    }
}
