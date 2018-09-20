package br.com.ez.finances.web.v1.controller;

import java.io.FileNotFoundException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ez.finances.api.v1.ITransactionController;
import br.com.ez.finances.api.v1.representation.transaction.TransactionRepresentation;
import br.com.ez.finances.domain.form.transaction.CreateTransaction;
import br.com.ez.finances.domain.form.transaction.UpdateTransaction;
import br.com.ez.finances.service.v1.ITransactionService;
import br.com.ez.finances.web.v1.mapper.TransactionMapper;

/**
 * Translation related API controller implementation.
 */
@RestController
public class TransactionController implements ITransactionController {

    private ITransactionService transactionService;

    private TransactionMapper mapper;

    @Autowired
    public TransactionController(ITransactionService transactionService, TransactionMapper mapper) {
        this.transactionService = transactionService;
        this.mapper = mapper;
    }

    @Override
    public Page<TransactionRepresentation> getTransactions(@RequestHeader("Profile-Id") Long profileId,
            Pageable pageable) {
        return mapper.toTransactionRepresentation(transactionService.getAllTransactions(profileId, pageable));
    }

    @Override
    public TransactionRepresentation searchTransaction(@RequestHeader("Profile-Id") Long profileId,
            @PathVariable Long id) {
        return mapper.toTransactionRepresentation(transactionService.searchTransaction(profileId, id));
    }

    @Override
    public TransactionRepresentation createTransaction(@RequestHeader("Profile-Id") Long profileId,
            @RequestBody @Valid CreateTransaction createTransaction) {
        return mapper.toTransactionRepresentation(transactionService.createTransaction(profileId, createTransaction));
    }

    @Override
    public TransactionRepresentation updateTransaction(@RequestHeader("Profile-Id") Long profileId,
            @PathVariable Long id, @RequestBody @Valid UpdateTransaction updateTransaction) {
        return mapper.toTransactionRepresentation(
                transactionService.updateTransaction(profileId, id, updateTransaction));
    }

    @Override
    public void deleteTransaction(@RequestHeader("Profile-Id") Long profileId,
            @PathVariable Long id) {
        transactionService.deleteTransaction(profileId, id);
    }

    @Override
    public List<TransactionRepresentation> uploadFile(@RequestHeader("Profile-Id") Long profileId,
            @RequestParam String filePath) throws FileNotFoundException {
        return mapper.fromTransactionDTO(transactionService.uploadFile(profileId, filePath));
    }
}
