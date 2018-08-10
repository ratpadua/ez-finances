package br.com.ez.finances.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.ez.finances.domain.entity.Translation;

/**
 * Transaction data transfer object.
 */
public class TransactionDTO {

    private Translation translation;

    private String description;

    private BigDecimal balance;

    private LocalDateTime inputDate;

    public TransactionDTO(Translation translation, String description, BigDecimal balance,
            LocalDateTime inputDate) {
        this.translation = translation;
        this.description = description;
        this.balance = balance;
        this.inputDate = inputDate;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
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

    public LocalDateTime getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDateTime inputDate) {
        this.inputDate = inputDate;
    }
}
