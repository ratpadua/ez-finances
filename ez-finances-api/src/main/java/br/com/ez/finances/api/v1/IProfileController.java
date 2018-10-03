package br.com.ez.finances.api.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.profile.CreateProfile;
import br.com.ez.finances.domain.form.profile.UpdateProfile;

/**
 * Profile related API controller.
 */
@RequestMapping("/v1/profile")
public interface IProfileController {

    /**
     * Searches all profiles with the provided statuses, if none is provided, all profiles are searched.
     *
     * @param statuses Optional request parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the profiles found.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProfileRepresentation> searchProfiles(@RequestParam(required = false) Status... statuses);

    /**
     * Searches the profile with the provided id.
     *
     * @param id Mandatory path variable with the id of the profile.
     * @return The profile found with the provided id.
     */
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProfileRepresentation getProfile(@PathVariable Long id);

    /**
     * Creates a new profile using the values provided on the request body.
     *
     * @param createProfile Mandatory valid json object with the new profile information.
     * @return The new profile created.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProfileRepresentation createProfile(@RequestBody @Valid CreateProfile createProfile);

    /**
     * Updates the profile with the provided id with the information provided on the request body.
     *
     * @param id            Mandatory path variable with the id of the profile.
     * @param updateProfile Mandatory valid json object with the values to be updated.
     * @return The updated profile.
     */
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProfileRepresentation updateProfile(@PathVariable Long id, @RequestBody @Valid UpdateProfile updateProfile);
}
