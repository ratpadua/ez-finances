package br.com.ez.finances.domain.form.translation;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to create a new translation.
 */
public class CreateTranslation {

    @NotEmpty
    private String description;

    @NotEmpty
    private String toDescription;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     *
     * @param description   Translate description.
     * @param toDescription Translate to.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private CreateTranslation(@JsonProperty("description") String description,
            @JsonProperty("toDescription") String toDescription) {
        this.description = description;
        this.toDescription = toDescription;
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param description   Mandatory parameter translate description.
     * @param toDescription Mandatory parameter translate to.
     * @return Create translation valid form.
     */
    public static CreateTranslation of(String description, String toDescription) {
        CreateTranslation createTranslation = new CreateTranslation(description, toDescription);

        ObjectValidator.validate(createTranslation);

        return createTranslation;
    }

    public String getDescription() {
        return description;
    }

    public String getToDescription() {
        return toDescription;
    }
}
