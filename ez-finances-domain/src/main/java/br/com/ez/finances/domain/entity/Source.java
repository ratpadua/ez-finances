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
import br.com.ez.finances.domain.form.source.CreateSource;

/**
 * Source entity with the table name, mapped columns and the ID sequence generator.
 */
@Entity
@Table(name = "SOURCE")
@SequenceGenerator(name = "SOURCE_ID_SEQ", sequenceName = "SOURCE_ID_SEQ")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOURCE_ID_SEQ")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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

    public static Source of(CreateSource createSource, Profile profile) {
        Source source = new Source();
        source.setProfile(profile);
        source.setName(createSource.getName());
        source.setStatus(Status.ACTIVE);

        return source;
    }
}
