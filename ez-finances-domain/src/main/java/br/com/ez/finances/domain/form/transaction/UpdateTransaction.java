package br.com.ez.finances.domain.form.transaction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.ez.finances.domain.enums.TransactionType;
import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to update a transaction.
 */
public class UpdateTransaction {

    private Optional<Long> sourceId;

    private Optional<TransactionType> type;

    private Optional<String> description;

    private Optional<BigDecimal> balance;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Optional<ZonedDateTime> inputDate;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private UpdateTransaction() {
        this.sourceId = Optional.empty();
        this.type = Optional.empty();
        this.description = Optional.empty();
        this.balance = Optional.empty();
        this.inputDate = Optional.empty();
    }

    /**
     * Static form builder requiring all parameters.
     *
     * @param sourceId    Optional parameter source id.
     * @param type        Optional parameter transaction type.
     * @param description Optional parameter description.
     * @param balance     Optional parameter balance change.
     * @param inputDate   Optional parameter input date.
     * @return Update transaction valid form.
     */
    public static UpdateTransaction of(Long sourceId, TransactionType type, String description, BigDecimal balance,
            ZonedDateTime inputDate) {
        UpdateTransaction updateTransaction = new UpdateTransaction();
        updateTransaction.setSourceId(sourceId);
        updateTransaction.setType(type);
        updateTransaction.setDescription(description);
        updateTransaction.setBalance(balance);
        updateTransaction.setInputDate(inputDate);

        ObjectValidator.validate(updateTransaction);

        return updateTransaction;
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

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

    public Optional<BigDecimal> getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = Optional.ofNullable(balance);
    }

    public Optional<ZonedDateTime> getInputDate() {
        return inputDate;
    }

    public void setInputDate(ZonedDateTime inputDate) {
        this.inputDate = Optional.ofNullable(inputDate);
    }
}
