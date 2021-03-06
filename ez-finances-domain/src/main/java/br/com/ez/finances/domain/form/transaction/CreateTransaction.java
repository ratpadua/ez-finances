package br.com.ez.finances.domain.form.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.enums.TransactionType;
import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to create a new transaction.
 */
public class CreateTransaction {

    @NotNull
    private Long sourceId;

    @NotNull
    private TransactionType type;

    @NotEmpty
    private String description;

    @NotNull
    private BigDecimal balance;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime inputDate;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     *
     * @param sourceId    Transaction source id.
     * @param type        Transaction type.
     * @param description Transaction description.
     * @param balance     Transaction balance change.
     * @param inputDate   Transaction input date.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private CreateTransaction(@JsonProperty("sourceId") Long sourceId,
            @JsonProperty("type") TransactionType type,
            @JsonProperty("description") String description,
            @JsonProperty("balance") BigDecimal balance,
            @JsonProperty("inputDate") LocalDateTime inputDate) {
        this.sourceId = sourceId;
        this.type = type;
        this.description = description;
        this.balance = balance;
        this.inputDate = inputDate;
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param sourceId    Mandatory parameter source id.
     * @param type        Mandatory parameter transaction type.
     * @param description Mandatory parameter description.
     * @param balance     Mandatory parameter balance change.
     * @param inputDate   Mandatory parameter input date.
     * @return Create transaction valid form.
     */
    public static CreateTransaction of(Long sourceId, TransactionType type, String description, BigDecimal balance,
            LocalDateTime inputDate) {
        CreateTransaction createTransaction = new CreateTransaction(sourceId, type, description, balance, inputDate);

        ObjectValidator.validate(createTransaction);

        return createTransaction;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getInputDate() {
        return inputDate;
    }
}
