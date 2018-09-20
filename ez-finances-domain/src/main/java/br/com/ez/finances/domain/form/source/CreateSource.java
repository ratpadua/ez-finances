package br.com.ez.finances.domain.form.source;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to create a new source.
 */
public class CreateSource {

    @NotEmpty
    private String name;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     *
     * @param name      Source name.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private CreateSource(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param name      Mandatory parameter source name.
     * @return Create source valid form.
     */
    public static CreateSource of(String name) {
        CreateSource createSource = new CreateSource(name);

        ObjectValidator.validate(createSource);

        return createSource;
    }

    public String getName() {
        return name;
    }
}
