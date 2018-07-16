package br.com.ez.finances.service.v1.impl;

import org.springframework.stereotype.Service;

import br.com.ez.finances.service.v1.IHelloWorldService;

/**
 * Created by raul.padua on 16/07/18
 */
@Service
public class HelloWorldService implements IHelloWorldService {

    @Override
    public String helloWorld() {
        return "Hello World V1";
    }
}
