package br.com.ez.finances.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.enums.Status;

/**
 * JPA Repository for the profile related operations.
 */
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    /**
     * Searches all profiles with the provided statuses and orders them by name.
     *
     * @param statuses Mandatory parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the profiles found.
     */
    List<Profile> findByStatusInOrderByName(Status... statuses);
}
