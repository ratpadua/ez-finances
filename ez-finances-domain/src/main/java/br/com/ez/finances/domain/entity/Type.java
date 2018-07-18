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
 * Type entity with the table name, mapped columns and the ID sequence generator.
 */
@Entity
@Table(name = "TYPE")
@SequenceGenerator(name = "TYPE_ID_SEQ", sequenceName = "TYPE_ID_SEQ")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TYPE_ID_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
