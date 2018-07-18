package br.com.ez.finances.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.profile.CreateProfile;

/**
 * Profile entity with the table name, mapped columns and the ID sequence generator.
 */
@Entity
@Table(name = "PROFILE")
@SequenceGenerator(name = "PROFILE_ID_SEQ", sequenceName = "PROFILE_ID_SEQ")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_ID_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
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

    public static Profile of(CreateProfile createProfile) {
        Profile profile = new Profile();
        profile.setName(createProfile.getName());

        if (createProfile.getBalance().isPresent()) {
            profile.setBalance(createProfile.getBalance().get());
        } else {
            profile.setBalance(BigDecimal.ZERO);
        }

        profile.setStatus(Status.ACTIVE);
        return profile;
    }
}
