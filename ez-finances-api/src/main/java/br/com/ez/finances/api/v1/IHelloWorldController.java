package br.com.ez.finances.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by raul.padua on 16/07/18
 */
public interface IHelloWorldController {

    @GetMapping(path = "/v1/hello")
    @ResponseStatus(HttpStatus.OK)
    String getCatalog();
}
