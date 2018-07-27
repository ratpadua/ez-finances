package br.com.ez.finances.domain.form.source;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to update a source.
 */
public class UpdateSource {

    private Optional<String> name;

    private Optional<Status> status;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private UpdateSource() {
        this.name = Optional.empty();
        this.status = Optional.empty();
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param name   Optional parameter source name.
     * @param status Optional parameter status.
     * @return Update translation valid form.
     */
    public static UpdateSource of(String name, Status status) {
        UpdateSource updateTranslation = new UpdateSource();
        updateTranslation.setName(name);
        updateTranslation.setStatus(status);

        ObjectValidator.validate(updateTranslation);

        return updateTranslation;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public Optional<Status> getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = Optional.ofNullable(status);
    }
}
