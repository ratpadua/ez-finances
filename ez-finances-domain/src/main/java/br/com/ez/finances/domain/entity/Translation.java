package br.com.ez.finances.domain.entity;

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

import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.enums.TransactionType;
import br.com.ez.finances.domain.form.translation.CreateTranslation;

/**
 * Translation entity with the table name, mapped columns and the ID sequence generator.
 */
@Entity
@Table(name = "TRANSLATION")
@SequenceGenerator(name = "TRANSLATION_ID_SEQ", sequenceName = "TRANSLATION_ID_SEQ")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSLATION_ID_SEQ")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "SOURCE_ID")
    private Source source;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TO_DESCRIPTION")
    private String toDescription;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

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

    public static Translation of(CreateTranslation createTranslation, Profile profile, Source source) {
        Translation translation = new Translation();
        translation.setProfile(profile);
        translation.setDescription(createTranslation.getDescription());
        translation.setToDescription(createTranslation.getToDescription());
        translation.setStatus(Status.ACTIVE);
        translation.setSource(source);
        createTranslation.getType().ifPresent(translation::setType);

        return translation;
    }
}
