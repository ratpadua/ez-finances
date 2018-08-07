package br.com.ez.finances.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import br.com.ez.finances.domain.entity.Transaction;

/**
 * JPA Repository for the transaction related operations.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Paginates all transactions.
     *
     * @param pageable Optional parameter pagination information.
     * @return A page with all the transactions found.
     */
    Page<Transaction> findAll(Pageable pageable);
}
