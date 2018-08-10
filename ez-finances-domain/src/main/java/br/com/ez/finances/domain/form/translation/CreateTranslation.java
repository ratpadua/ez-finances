package br.com.ez.finances.domain.form.translation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.enums.TransactionType;
import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to create a new translation.
 */
public class CreateTranslation {

    @NotNull
    private Long sourceId;

    @NotNull
    private TransactionType type;

    @NotEmpty
    private String description;

    @NotEmpty
    private String toDescription;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     *
     * @param sourceId      Transaction source.
     * @param type          Transaction type.
     * @param description   Translate from description.
     * @param toDescription Translate to description.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private CreateTranslation(@JsonProperty("sourceId") Long sourceId,
            @JsonProperty("type") TransactionType type,
            @JsonProperty("description") String description,
            @JsonProperty("toDescription") String toDescription) {
        this.description = description;
        this.type = type;
        this.toDescription = toDescription;
        this.sourceId = sourceId;
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param sourceId      Mandatory parameter source id.
     * @param type          Mandatory parameter transaction type.
     * @param description   Mandatory parameter translate description.
     * @param toDescription Mandatory parameter translate to.
     * @return Create translation valid form.
     */
    public static CreateTranslation of(Long sourceId, TransactionType type, String description, String toDescription) {
        CreateTranslation createTranslation = new CreateTranslation(sourceId, type, description, toDescription);

        ObjectValidator.validate(createTranslation);

        return createTranslation;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getToDescription() {
        return toDescription;
    }
}
