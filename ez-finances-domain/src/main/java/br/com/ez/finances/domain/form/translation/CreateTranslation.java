package br.com.ez.finances.domain.form.translation;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.enums.TransactionType;
import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to create a new translation.
 */
public class CreateTranslation {

    @NotEmpty
    private String description;

    @NotEmpty
    private String toDescription;

    private Optional<Long> sourceId;

    private Optional<TransactionType> type;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     *
     * @param description   Translate from description.
     * @param toDescription Translate to description.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private CreateTranslation(@JsonProperty("description") String description,
            @JsonProperty("toDescription") String toDescription) {
        this.description = description;
        this.toDescription = toDescription;
        this.sourceId = Optional.empty();
        this.type = Optional.empty();
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

    /**
     * Static form builder requiring with all parameters.
     *
     * @param description   Mandatory parameter translate description.
     * @param toDescription Mandatory parameter translate to.
     * @param sourceId      Mandatory parameter source id.
     * @param type          Mandatory parameter transaction type.
     * @return Create translation valid form.
     */
    public static CreateTranslation of(String description, String toDescription, Long sourceId, TransactionType type) {
        CreateTranslation createTranslation = new CreateTranslation(description, toDescription);
        createTranslation.setSourceId(sourceId);
        createTranslation.setType(type);

        ObjectValidator.validate(createTranslation);

        return createTranslation;
    }

    public String getDescription() {
        return description;
    }

    public String getToDescription() {
        return toDescription;
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
}
