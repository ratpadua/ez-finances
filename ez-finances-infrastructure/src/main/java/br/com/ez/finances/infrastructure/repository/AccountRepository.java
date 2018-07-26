package br.com.ez.finances.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ez.finances.domain.entity.Account;

/**
 * JPA Repository for the Translation related operations.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}
