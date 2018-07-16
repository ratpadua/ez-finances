package br.com.ez.finances.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by raul.padua on 16/07/18
 */
@SpringBootApplication
@ComponentScan(basePackages = "br.com.ez.finances")
@EnableAutoConfiguration
public class EZApplication {

    public static void main(String[] args) {
        SpringApplication.run(EZApplication.class, args);
    }
}
