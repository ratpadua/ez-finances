package br.com.ez.finances.web.v1.mapper;

import org.mapstruct.Mapper;

import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;
import br.com.ez.finances.domain.entity.Profile;

/**
 * Created by raul.padua on 17/07/18
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileRepresentation toProfileRepresentation(Profile profile);
}
