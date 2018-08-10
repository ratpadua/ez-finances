package br.com.ez.finances.service.v1;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ez.finances.domain.dto.TransactionDTO;
import br.com.ez.finances.domain.entity.Transaction;
import br.com.ez.finances.domain.form.transaction.CreateTransaction;
import br.com.ez.finances.domain.form.transaction.UpdateTransaction;

/**
 * Interface containing Transaction related operations.
 */
public interface ITransactionService {

    /**
     * Paginates all transactions.
     *
     * @param pageable Optional parameter pagination information.
     * @return A page with all the transactions found.
     */
    Page<Transaction> getAllTransactions(Pageable pageable);

    /**
     * Creates a new transaction using the values provided on the form object.
     *
     * @param createTransaction Mandatory valid object with the new transaction information.
     * @return The new transaction created.
     */
    Transaction createTransaction(CreateTransaction createTransaction);

    /**
     * Updates the transaction with the provided id with the information provided on the form object.
     *
     * @param id                Mandatory variable with the id of the transaction.
     * @param updateTransaction Mandatory valid object with the values to be updated.
     * @return The updated transaction.
     */
    Transaction updateTransaction(Long id, UpdateTransaction updateTransaction);

    /**
     * Reads a file and returns the transactions found.
     *
     * @param filePath The file location.
     * @return A list of transactions.
     */
    List<TransactionDTO> uploadFile(String filePath);
}
