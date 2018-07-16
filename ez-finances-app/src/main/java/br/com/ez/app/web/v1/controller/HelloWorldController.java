package br.com.ez.app.web.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by raul.padua on 16/07/18
 */
@RestController
public class HelloWorldController {

    @GetMapping(path = "/v1/hello")
    @ResponseStatus(HttpStatus.OK)
    public String getCatalog() {
        return "Hello V1";
    }
}
