package br.com.ez.finances.web.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.ez.finances.api.v1.IHelloWorldController;
import br.com.ez.finances.service.v1.IHelloWorldService;

/**
 * Created by raul.padua on 16/07/18
 */
@RestController
public class HelloWorldController implements IHelloWorldController {

    private IHelloWorldService helloWorldService;

    @Autowired
    public HelloWorldController(IHelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @Override
    public String getCatalog() {
        return helloWorldService.helloWorld();
    }
}
