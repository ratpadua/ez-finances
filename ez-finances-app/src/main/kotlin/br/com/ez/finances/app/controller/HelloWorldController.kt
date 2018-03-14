package br.com.ez.finances.app.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/hello")
    fun helloWorld() : String {
        return "HelloWorld"
    }
}