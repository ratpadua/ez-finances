package br.com.ez.finances.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ez.finances.domain.entity.Source;
import br.com.ez.finances.domain.enums.Status;

/**
 * JPA Repository for the source related operations.
 */
public interface SourceRepository extends JpaRepository<Source, Long> {

    /**
     * Searches all sources with the provided statuses and orders them by name.
     *
     * @param statuses Mandatory parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the souces found.
     */
    List<Source> findByStatusInOrderByName(Status... statuses);
}
