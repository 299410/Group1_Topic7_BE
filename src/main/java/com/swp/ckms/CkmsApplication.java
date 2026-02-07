package com.swp.ckms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CkmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CkmsApplication.class, args);
    }

}
