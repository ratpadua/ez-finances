package br.com.ez.finances.domain.form.translation;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to update a translation.
 */
public class UpdateTranslation {

    private Optional<String> toDescription;

    private Optional<Status> status;

    private Optional<Long> sourceId;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private UpdateTranslation() {
        this.toDescription = Optional.empty();
        this.status = Optional.empty();
        this.sourceId = Optional.empty();
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param toDescription Optional parameter translate to.
     * @param status        Optional parameter status.
     * @param sourceId      Optional parameter source id.
     * @return Update translation valid form.
     */
    public static UpdateTranslation of(String toDescription, Status status, Long sourceId) {
        UpdateTranslation updateTranslation = new UpdateTranslation();
        updateTranslation.setToDescription(toDescription);
        updateTranslation.setStatus(status);
        updateTranslation.setSourceId(sourceId);

        ObjectValidator.validate(updateTranslation);

        return updateTranslation;
    }

    public Optional<String> getToDescription() {
        return toDescription;
    }

    public void setToDescription(String toDescription) {
        this.toDescription = Optional.ofNullable(toDescription);
    }

    public Optional<Status> getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = Optional.ofNullable(status);
    }

    public Optional<Long> getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = Optional.ofNullable(sourceId);
    }
}
