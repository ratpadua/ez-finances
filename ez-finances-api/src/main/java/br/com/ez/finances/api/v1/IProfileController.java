package br.com.ez.finances.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;

/**
 * Created by raul.padua on 16/07/18
 */
@RequestMapping("/v1/profile")
public interface IProfileController {

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProfileRepresentation searchProfile(@PathVariable Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProfileRepresentation createProfile();
}
