package br.com.ez.finances.api.v1.representation.translation;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ez.finances.api.v1.representation.source.SourceRepresentation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.enums.TransactionType;

/**
 * Rest response translation representation. Any null values are not included in the JSON.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TranslationRepresentation {

    private Long id;

    private SourceRepresentation source;

    private TransactionType type;

    private String description;

    private String toDescription;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SourceRepresentation getSource() {
        return source;
    }

    public void setSource(SourceRepresentation source) {
        this.source = source;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToDescription() {
        return toDescription;
    }

    public void setToDescription(String toDescription) {
        this.toDescription = toDescription;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
