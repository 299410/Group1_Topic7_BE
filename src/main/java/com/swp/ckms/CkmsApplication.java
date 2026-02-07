package com.swp.ckms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.swp.ckms.config.AppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class CkmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CkmsApplication.class, args);
    }

}
