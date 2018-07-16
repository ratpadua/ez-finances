package br.com.ez.finances.web.v1.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.ez.finances.api.v1.IHelloWorldController;

/**
 * Created by raul.padua on 16/07/18
 */
@RestController
public class HelloWorldController implements IHelloWorldController {

    @Override
    public String getCatalog() {
        return "Hello V1";
    }
}
