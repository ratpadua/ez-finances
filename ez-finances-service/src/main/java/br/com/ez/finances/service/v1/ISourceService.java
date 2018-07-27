package br.com.ez.finances.service.v1;

import java.util.List;

import br.com.ez.finances.domain.entity.Source;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.source.CreateSource;
import br.com.ez.finances.domain.form.source.UpdateSource;

/**
 * Interface containing Profile related operations.
 */
public interface ISourceService {

    /**
     * Searches all sources with the provided statuses, if none is provided, all sources are searched.
     *
     * @param statuses Optional parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the sources found.
     */
    List<Source> getSources(Status... statuses);

    /**
     * Searches the source with the provided id.
     *
     * @param id Mandatory variable with the id of the source.
     * @return The source found with the provided id.
     */
    Source searchSource(Long id);

    /**
     * Creates a new source using the values provided on the form object.
     *
     * @param createSource Mandatory valid object with the new source information.
     * @return The new source created.
     */
    Source createSource(CreateSource createSource);

    /**
     * Updates the source with the provided id with the information provided on the form object.
     *
     * @param id           Mandatory variable with the id of the source.
     * @param updateSource Mandatory valid object with the values to be updated.
     * @return The updated source.
     */
    Source updateSource(Long id, UpdateSource updateSource);
}
