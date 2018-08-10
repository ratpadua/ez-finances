package br.com.ez.finances.domain.form.translation;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.enums.TransactionType;
import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to update a translation.
 */
public class UpdateTranslation {

    private Optional<Long> sourceId;

    private Optional<TransactionType> type;

    private Optional<String> toDescription;

    private Optional<Status> status;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private UpdateTranslation() {
        this.sourceId = Optional.empty();
        this.type = Optional.empty();
        this.toDescription = Optional.empty();
        this.status = Optional.empty();
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param sourceId      Optional parameter source id.
     * @param type          Optional parameter transaction type.
     * @param toDescription Optional parameter translate to.
     * @param status        Optional parameter status.
     * @return Update translation valid form.
     */
    public static UpdateTranslation of(Long sourceId, TransactionType type, String toDescription, Status status) {
        UpdateTranslation updateTranslation = new UpdateTranslation();
        updateTranslation.setSourceId(sourceId);
        updateTranslation.setToDescription(toDescription);
        updateTranslation.setStatus(status);

        ObjectValidator.validate(updateTranslation);

        return updateTranslation;
    }

    public Optional<Long> getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = Optional.ofNullable(sourceId);
    }

    public Optional<TransactionType> getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = Optional.ofNullable(type);
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
}
