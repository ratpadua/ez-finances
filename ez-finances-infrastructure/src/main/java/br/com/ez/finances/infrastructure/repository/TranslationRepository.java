package br.com.ez.finances.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ez.finances.domain.entity.Translation;
import br.com.ez.finances.domain.enums.Status;

/**
 * JPA Repository for the translation related operations.
 */
public interface TranslationRepository extends JpaRepository<Translation, Long> {

    /**
     * Searches all translations with the provided statuses and orders them by description.
     *
     * @param statuses Mandatory parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the translations found.
     */
    List<Translation> findByStatusInOrderByDescription(Status... statuses);

    /**
     * Searches the translation from a provided description.
     *
     * @param description The description to be translated.
     * @return The translation found.
     */
    Translation findByDescriptionEquals(String description);
}
