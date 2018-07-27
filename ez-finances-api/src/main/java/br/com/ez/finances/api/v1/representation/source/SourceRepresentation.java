package br.com.ez.finances.api.v1.representation.source;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;
import br.com.ez.finances.domain.enums.Status;

/**
 * Rest response source representation. Any null values are not included in the JSON.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceRepresentation {

    private Long id;

    private ProfileRepresentation profile;

    private String name;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfileRepresentation getProfile() {
        return profile;
    }

    public void setProfile(ProfileRepresentation profile) {
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
}
