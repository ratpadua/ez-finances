package br.com.ez.finances.infrastructure.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.entity.Translation;
import br.com.ez.finances.domain.enums.Status;

/**
 * JPA Repository for the translation related operations.
 */
public interface TranslationRepository extends CrudRepository<Translation, Long> {

    /**
     * Searches all translations with the provided statuses and orders them by description.
     *
     * @param profile  The profile to be searched.
     * @param statuses Mandatory parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the translations found.
     */
    List<Translation> findByProfileEqualsAndStatusInOrderByDescription(Profile profile, Status... statuses);

    /**
     * Searches the translation from a provided description.
     *
     * @param profile     The profile to be searched.
     * @param description The description to be translated.
     * @return The translation found.
     */
    Translation findByProfileEqualsAndDescriptionEquals(Profile profile, String description);
}
