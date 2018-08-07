package br.com.ez.finances.api.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ez.finances.api.v1.representation.transaction.TransactionRepresentation;
import br.com.ez.finances.api.v1.representation.translation.TranslationRepresentation;
import br.com.ez.finances.domain.form.transaction.CreateTransaction;
import br.com.ez.finances.domain.form.transaction.UpdateTransaction;

/**
 * Transaction related API controller.
 */
@RequestMapping("/v1/transaction")
public interface ITransactionController {

    /**
     * Paginates all transactions.
     *
     * @param pageable Optional request parameters pagination information.
     * @return A page with all the transactions found.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<TransactionRepresentation> getAllTransactions(Pageable pageable);

    /**
     * Creates a new transaction using the values provided on the request body.
     *
     * @param createTransaction Mandatory valid json object with the new transaction information.
     * @return The new transaction created.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TransactionRepresentation createTransaction(@RequestBody @Valid CreateTransaction createTransaction);

    /**
     * Updates the transaction with the provided id with the information provided on the request body.
     *
     * @param id                Mandatory path variable with the id of the transaction.
     * @param updateTransaction Mandatory valid json object with the values to be updated.
     * @return The updated transaction.
     */
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    TransactionRepresentation updateTransaction(@PathVariable Long id,
            @RequestBody @Valid UpdateTransaction updateTransaction);
}
