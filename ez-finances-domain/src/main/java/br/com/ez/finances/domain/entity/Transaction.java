package br.com.ez.finances.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
import br.com.ez.finances.domain.form.transaction.CreateTransaction;

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
    @JoinColumn(name = "SOURCE_ID")
    private Source source;

    @ManyToOne
    @JoinColumn(name = "TRANSLATION_ID")
    private Translation translation;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "INPUT_DATE")
    private LocalDateTime inputDate;

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

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
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

    public LocalDateTime getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDateTime inputDate) {
        this.inputDate = inputDate;
    }

    public static Transaction of(CreateTransaction createTransaction, Profile profile, Source source,
            Translation translation) {

        Transaction transaction = new Transaction();
        transaction.setProfile(profile);
        transaction.setSource(source);
        transaction.setType(createTransaction.getType());
        transaction.setDescription(createTransaction.getDescription());
        transaction.setBalance(createTransaction.getBalance());
        transaction.setInputDate(createTransaction.getInputDate());

        if (translation != null) {
            transaction.setDescription(translation.getToDescription());
            transaction.setTranslation(translation);
        }

        return transaction;
    }
}
