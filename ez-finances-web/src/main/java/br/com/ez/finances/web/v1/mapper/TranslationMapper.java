package br.com.ez.finances.web.v1.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;
import br.com.ez.finances.api.v1.representation.translation.TranslationRepresentation;
import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.entity.Translation;

/**
 * Translation related mapper utility. Uses Mapstruct Framework to generate implementation.
 */
@Mapper(componentModel = "spring")
public interface TranslationMapper {

    /**
     * Converts the Translation entity into a rest response representation of it.
     *
     * @param translation The translation entity.
     * @return A rest response representation of a translation.
     */
    TranslationRepresentation toTranslationRepresentation(Translation translation);

    /**
     * Converts a list of Translation entities into a rest response representation list of it.
     *
     * @param translations The translation entities.
     * @return A rest response representation of a list of translations.
     */
    List<TranslationRepresentation> toTranslationRepresentation(List<Translation> translations);
}
