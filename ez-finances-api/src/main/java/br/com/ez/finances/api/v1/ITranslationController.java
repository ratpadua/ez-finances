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
import br.com.ez.finances.api.v1.representation.translation.TranslationRepresentation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.profile.CreateProfile;
import br.com.ez.finances.domain.form.profile.UpdateProfile;
import br.com.ez.finances.domain.form.translation.CreateTranslation;
import br.com.ez.finances.domain.form.translation.UpdateTranslation;

/**
 * Translation related API controller.
 */
@RequestMapping("/v1/translation")
public interface ITranslationController {

    /**
     * Searches all translations with the provided statuses, if none is provided, all translations are searched.
     *
     * @param profileId Mandatory request parameter with the profile id.
     * @param statuses Optional request parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the translations found.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TranslationRepresentation> getTranslations(@RequestParam Long profileId,
            @RequestParam(required = false) Status... statuses);

    /**
     * Searches the translation of a description.
     *
     * @param description Mandatory request parameter with the description to be translated.
     * @return The translation found for the description.
     */
    @GetMapping(path = "/search")
    @ResponseStatus(HttpStatus.OK)
    TranslationRepresentation searchTranslation(@RequestParam String description);

    /**
     * Creates a new translation using the values provided on the request body.
     *
     * @param createTranslation Mandatory valid json object with the new translation information.
     * @return The new translation created.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TranslationRepresentation createTranslation(@RequestBody @Valid CreateTranslation createTranslation);

    /**
     * Updates the translation with the provided id with the information provided on the request body.
     *
     * @param id                Mandatory path variable with the id of the translation.
     * @param updateTranslation Mandatory valid json object with the values to be updated.
     * @return The updated translation.
     */
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    TranslationRepresentation updateTranslation(@PathVariable Long id,
            @RequestBody @Valid UpdateTranslation updateTranslation);
}
