package br.com.ez.finances.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ez.finances.domain.entity.Transaction;

/**
 * JPA Repository for the transaction related operations.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
