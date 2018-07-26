package br.com.ez.finances.domain.form.translation;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to create a new translation.
 */
public class SearchTranslation {

    @NotEmpty
    private String description;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     *
     * @param description   Translate description.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private SearchTranslation(@JsonProperty("description") String description) {
        this.description = description;
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param description Mandatory parameter translate description.
     * @return Create translation valid form.
     */
    public static SearchTranslation of(String description) {
        SearchTranslation createTranslation = new SearchTranslation(description);

        ObjectValidator.validate(createTranslation);

        return createTranslation;
    }

    public String getDescription() {
        return description;
    }
}
