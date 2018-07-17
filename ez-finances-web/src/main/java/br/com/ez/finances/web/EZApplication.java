package br.com.ez.finances.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by raul.padua on 16/07/18
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
