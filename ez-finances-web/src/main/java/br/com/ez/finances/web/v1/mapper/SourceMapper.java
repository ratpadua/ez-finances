package br.com.ez.finances.web.v1.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.ez.finances.api.v1.representation.source.SourceRepresentation;
import br.com.ez.finances.domain.entity.Source;

/**
 * Profile related mapper utility. Uses Mapstruct Framework to generate implementation.
 */
@Mapper(componentModel = "spring")
public interface SourceMapper {

    /**
     * Converts the Source entity into a rest response representation of it.
     *
     * @param source The source entity.
     * @return A rest response representation of a source.
     */
    SourceRepresentation toSourceRepresentation(Source source);

    /**
     * Converts a list of Source entities into a rest response representation list of it.
     *
     * @param sources The source entities.
     * @return A rest response representation of a list of sources.
     */
    List<SourceRepresentation> toSourceRepresentation(List<Source> sources);
}
