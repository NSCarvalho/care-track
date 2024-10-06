package com.caretrack.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.caretrack.rest, " +
        "com.caretrack.rest, com.caretrack.business")
@EnableJpaRepositories(basePackages = "com.caretrack.business.repos")
@EntityScan(basePackages = "com.caretrack.business.repos.entities")
@SpringBootApplication
public class CareTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareTrackApplication.class, args);
    }

}
