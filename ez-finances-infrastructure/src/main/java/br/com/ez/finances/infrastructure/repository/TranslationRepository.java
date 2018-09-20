package br.com.ez.finances.infrastructure.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ez.finances.domain.entity.Translation;
import br.com.ez.finances.domain.enums.Status;

/**
 * JPA Repository for the translation related operations.
 */
public interface TranslationRepository extends CrudRepository<Translation, Long> {

    /**
     * Searches all translations with the provided statuses and orders them by description.
     *
     * @param profileId The profile to be searched.
     * @param statuses  Mandatory parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the translations found.
     */
    List<Translation> findByProfileIdEqualsAndStatusInOrderByDescription(Long profileId, Status... statuses);

    /**
     * Searches the translation from a provided description.
     *
     * @param profileId   The profile to be searched.
     * @param description The description to be translated.
     * @return The translation found.
     */
    Translation findByProfileIdEqualsAndDescriptionEquals(Long profileId, String description);
}
