package br.com.ez.finances.api.v1;

import java.io.FileNotFoundException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ez.finances.api.v1.representation.transaction.TransactionRepresentation;
import br.com.ez.finances.domain.form.transaction.CreateTransaction;
import br.com.ez.finances.domain.form.transaction.UpdateTransaction;
import br.com.ez.finances.domain.form.transaction.UploadTransactions;

/**
 * Transaction related API controller.
 */
@RequestMapping("/v1/transaction")
public interface ITransactionController {

    /**
     * Paginates all transactions.
     *
     * @param profileId Mandatory header parameter with the profile id.
     * @param pageable  Optional request parameters pagination information.
     * @return A page with all the transactions found.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<TransactionRepresentation> searchTransactions(@RequestHeader("Profile-Id") Long profileId, Pageable pageable);

    /**
     * Searches a transaction by id.
     *
     * @param profileId Mandatory header parameter with the profile id.
     * @param id        Mandatory path variable with the id of the transaction.
     * @return The translation found.
     */
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    TransactionRepresentation getTransaction(@RequestHeader("Profile-Id") Long profileId, @PathVariable Long id);

    /**
     * Creates a new transaction using the values provided on the request body.
     *
     * @param profileId         Mandatory header parameter with the profile id.
     * @param createTransaction Mandatory valid json object with the new transaction information.
     * @return The new transaction created.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TransactionRepresentation createTransaction(@RequestHeader("Profile-Id") Long profileId,
            @RequestBody @Valid CreateTransaction createTransaction);

    /**
     * Updates the transaction with the provided id with the information provided on the request body.
     *
     * @param profileId         Mandatory header parameter with the profile id.
     * @param id                Mandatory path variable with the id of the transaction.
     * @param updateTransaction Mandatory valid json object with the values to be updated.
     * @return The updated transaction.
     */
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    TransactionRepresentation updateTransaction(@RequestHeader("Profile-Id") Long profileId, @PathVariable Long id,
            @RequestBody @Valid UpdateTransaction updateTransaction);

    /**
     * Deletes the transaction with the provided id.
     *
     * @param profileId Mandatory header parameter with the profile id.
     * @param id        Mandatory path variable with the id of the transaction.
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteTransaction(@RequestHeader("Profile-Id") Long profileId, @PathVariable Long id);

    /**
     * Reads a file and returns the transactions found.
     *
     * @param profileId Mandatory header parameter with the profile id.
     * @param filePath  The file location.
     * @return A list of transactions.
     */
    @GetMapping(path = "/upload")
    @ResponseStatus(HttpStatus.OK)
    List<TransactionRepresentation> uploadFile(@RequestHeader("Profile-Id") Long profileId,
            @RequestParam String filePath) throws FileNotFoundException;

    /**
     * Creates transactions using the values provided on the request body.
     *
     * @param profileId          Mandatory header parameter with the profile id.
     * @param uploadTransactions Mandatory valid json array object with transaction information.
     * @return The new transaction created.
     */
    @PostMapping(path = "/upload")
    @ResponseStatus(HttpStatus.CREATED)
    List<TransactionRepresentation> uploadTransactions(@RequestHeader("Profile-Id") Long profileId,
            @RequestBody @Valid UploadTransactions uploadTransactions);
}
