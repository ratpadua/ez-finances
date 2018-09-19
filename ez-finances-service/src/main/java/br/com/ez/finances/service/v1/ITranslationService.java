package br.com.ez.finances.service.v1;

import java.util.List;

import br.com.ez.finances.domain.entity.Translation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.translation.CreateTranslation;
import br.com.ez.finances.domain.form.translation.UpdateTranslation;

/**
 * Interface containing Translation related operations.
 */
public interface ITranslationService {

    /**
     * Searches all translations with the provided statuses, if none is provided, all translations are searched.
     *
     * @param profileId Mandatory parameter with the profile id.
     * @param statuses Optional parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the translations found.
     */
    List<Translation> getTranslations(Long profileId, Status... statuses);

    /**
     * Searches the translation of a description.
     *
     * @param description Mandatory variable with description to be translated.
     * @return The translation found for the description.
     */
    Translation searchTranslation(String description);

    /**
     * Creates a new translation using the values provided on the form object.
     *
     * @param createTranslation Mandatory valid object with the new translation information.
     * @return The new translation created.
     */
    Translation createTranslation(CreateTranslation createTranslation);

    /**
     * Updates the translation with the provided id with the information provided on the form object.
     *
     * @param id                Mandatory variable with the id of the translation.
     * @param updateTranslation Mandatory valid object with the values to be updated.
     * @return The updated translation.
     */
    Translation updateTranslation(Long id, UpdateTranslation updateTranslation);
}
