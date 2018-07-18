package br.com.ez.finances.web.v1.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;
import br.com.ez.finances.domain.entity.Profile;

/**
 * Profile related mapper utility. Uses Mapstruct Framework to generate implementation.
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    /**
     * Converts the Profile entity into a rest response representation of it.
     * @param profile The profile entity.
     * @return A rest response representation of a profile.
     */
    ProfileRepresentation toProfileRepresentation(Profile profile);

    /**
     * Converts a list of Profile entities into a rest response representation list of it.
     * @param profiles The profile entities.
     * @return A rest response representation of a list of profiles.
     */
    List<ProfileRepresentation> toProfileRepresentation(List<Profile> profiles);
}
