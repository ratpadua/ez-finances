package br.com.ez.finances.api.v1.representation.profile;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ez.finances.domain.enums.Status;

/**
 * Rest response profile representation. Any null values are not included in the JSON.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileRepresentation {

    private Long id;

    private String name;

    private BigDecimal balance;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
