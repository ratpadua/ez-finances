package br.com.ez.finances.web.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Page<TransactionRepresentation> getAllTransactions(Pageable pageable) {
        return mapper.toTransactionRepresentation(transactionService.getAllTransactions(pageable));
    }

    @Override
    public TransactionRepresentation createTransaction(@RequestBody @Valid CreateTransaction createTransaction) {
        return mapper.toTransactionRepresentation(transactionService.createTransaction(createTransaction));
    }

    @Override
    public TransactionRepresentation updateTransaction(@PathVariable Long id,
            @RequestBody @Valid UpdateTransaction updateTransaction) {
        return mapper.toTransactionRepresentation(transactionService.updateTransaction(id, updateTransaction));
    }
}
