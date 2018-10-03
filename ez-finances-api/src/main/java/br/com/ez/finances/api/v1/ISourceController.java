package br.com.ez.finances.api.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ez.finances.api.v1.representation.source.SourceRepresentation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.source.CreateSource;
import br.com.ez.finances.domain.form.source.UpdateSource;

/**
 * Source related API controller.
 */
@RequestMapping("/v1/source")
public interface ISourceController {

    /**
     * Searches all sources with the provided statuses, if none is provided, all sources are searched.
     *
     * @param profileId Mandatory header parameter with the profile id.
     * @param statuses  Optional request parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the sources found.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<SourceRepresentation> searchSources(@RequestHeader("Profile-Id") Long profileId,
            @RequestParam(required = false) Status... statuses);

    /**
     * Searches the source with the provided id.
     *
     * @param profileId Mandatory header parameter with the profile id.
     * @param id        Mandatory path variable with the id of the source.
     * @return The source found with the provided id.
     */
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    SourceRepresentation getSource(@RequestHeader("Profile-Id") Long profileId, @PathVariable Long id);

    /**
     * Creates a new source using the values provided on the request body.
     *
     * @param profileId    Mandatory header parameter with the profile id.
     * @param createSource Mandatory valid json object with the new source information.
     * @return The new source created.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SourceRepresentation createSource(@RequestHeader("Profile-Id") Long profileId,
            @RequestBody @Valid CreateSource createSource);

    /**
     * Updates the source with the provided id with the information provided on the request body.
     *
     * @param profileId    Mandatory header parameter with the profile id.
     * @param id           Mandatory path variable with the id of the source.
     * @param updateSource Mandatory valid json object with the values to be updated.
     * @return The updated source.
     */
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    SourceRepresentation updateSource(@RequestHeader("Profile-Id") Long profileId, @PathVariable Long id,
            @RequestBody @Valid UpdateSource updateSource);
}
