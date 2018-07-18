package br.com.ez.finances.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * EZ Finances application. Uses Spring Boot with Undertow Server, auto configured by the spring framework. Scans
 * components, entities and enable JPA repositories within the packages br.com.ez.finances.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.ez.finances")
@EntityScan(basePackages = "br.com.ez.finances")
@EnableJpaRepositories(basePackages = "br.com.ez.finances")
public class EZApplication {

    public static void main(String[] args) {
        SpringApplication.run(EZApplication.class, args);
    }
}
