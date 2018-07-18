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

    @Column(name = "FROM_DESCRIPTION")
    private String from;

    @Column(name = "TO_DESCRIPTION")
    private String to;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
