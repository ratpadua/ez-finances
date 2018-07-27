package br.com.ez.finances.domain.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ez.finances.domain.enums.TransactionType;

/**
 * Transaction entity with the table name, mapped columns and the ID sequence generator.
 */
@Entity
@Table(name = "TRANSACTION")
@SequenceGenerator(name = "TRANSACTION_ID_SEQ", sequenceName = "TRANSACTION_ID_SEQ")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_ID_SEQ")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "TRANSLATION_ID")
    private Translation translation;

    @ManyToOne
    @JoinColumn(name = "SOURCE_ID")
    private Source source;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TransactionType status;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "INPUT_DATE")
    private ZonedDateTime inputDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }


    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public TransactionType getStatus() {
        return status;
    }

    public void setStatus(TransactionType status) {
        this.status = status;
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
