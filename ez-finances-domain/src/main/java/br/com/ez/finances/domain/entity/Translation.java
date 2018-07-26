package br.com.ez.finances.domain.entity;

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

    public static Translation of(CreateTranslation createTranslation) {
        Translation translation = new Translation();
        translation.setDescription(createTranslation.getDescription());
        translation.setToDescription(createTranslation.getToDescription());
        translation.setStatus(Status.ACTIVE);

        return translation;
    }
}
