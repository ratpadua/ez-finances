package br.com.ez.finances.api.v1.representation.transaction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ez.finances.api.v1.representation.source.SourceRepresentation;
import br.com.ez.finances.api.v1.representation.translation.TranslationRepresentation;
import br.com.ez.finances.domain.enums.TransactionType;

/**
 * Rest response transaction representation. Any null values are not included in the JSON.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionRepresentation {

    private Long id;

    private SourceRepresentation source;

    private TranslationRepresentation translation;

    private TransactionType type;

    private String description;

    private BigDecimal balance;

    private ZonedDateTime inputDate;

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

    public TranslationRepresentation getTranslation() {
        return translation;
    }

    public void setTranslation(TranslationRepresentation translation) {
        this.translation = translation;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ZonedDateTime getInputDate() {
        return inputDate;
    }

    public void setInputDate(ZonedDateTime inputDate) {
        this.inputDate = inputDate;
    }
}
