package br.com.ez.finances.service.v1.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webcohesion.ofx4j.domain.data.MessageSetType;
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.domain.data.ResponseMessageSet;
import com.webcohesion.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import com.webcohesion.ofx4j.domain.data.banking.BankingResponseMessageSet;
import com.webcohesion.ofx4j.io.AggregateUnmarshaller;

import br.com.ez.finances.domain.dto.TransactionDTO;
import br.com.ez.finances.domain.entity.Source;
import br.com.ez.finances.domain.entity.Transaction;
import br.com.ez.finances.domain.entity.Translation;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.domain.form.transaction.CreateTransaction;
import br.com.ez.finances.domain.form.transaction.UpdateTransaction;
import br.com.ez.finances.infrastructure.repository.TransactionRepository;
import br.com.ez.finances.service.v1.IProfileService;
import br.com.ez.finances.service.v1.ISourceService;
import br.com.ez.finances.service.v1.ITransactionService;
import br.com.ez.finances.service.v1.ITranslationService;

/**
 * Implementation of the transaction related operations.
 */
@Service
public class TransactionService implements ITransactionService {

    private TransactionRepository transactionRepository;

    private IProfileService profileService;

    private ISourceService sourceService;

    private ITranslationService translationService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, IProfileService profileService,
            ISourceService sourceService, ITranslationService translationService) {
        this.transactionRepository = transactionRepository;
        this.profileService = profileService;
        this.sourceService = sourceService;
        this.translationService = translationService;
    }

    @Override
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Transaction createTransaction(CreateTransaction createTransaction) {
        Source source = sourceService.searchSource(createTransaction.getSourceId());
        Translation translation = translationService.searchTranslation(createTransaction.getDescription());
        Transaction transaction = Transaction.of(createTransaction, source, translation);

        profileService.addBalance(source.getProfile().getId(), transaction.getBalance());

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction updateTransaction(Long id, UpdateTransaction updateTransaction) {
        Optional<Transaction> optTransaction = transactionRepository.findById(id);

        if (!optTransaction.isPresent()) throw new RuntimeException(ErrorCode.ERR_900.getCode());

        Transaction transaction = optTransaction.get();

        updateTransaction.getSourceId().ifPresent(sourceId -> {
            Source source = sourceService.searchSource(sourceId);
            transaction.setSource(source);
        });

        updateTransaction.getType().ifPresent(transaction::setType);
        updateTransaction.getInputDate().ifPresent(transaction::setInputDate);

        updateTransaction.getBalance().ifPresent(balance -> {
            BigDecimal prevBalance = transaction.getBalance();
            profileService.addBalance(transaction.getSource().getProfile().getId(), balance.subtract(prevBalance));
            transaction.setBalance(balance);
        });

        updateTransaction.getDescription().ifPresent(description -> {
            Translation translation = translationService.searchTranslation(description);

            if (translation != null) {
                transaction.setDescription(translation.getToDescription());
                transaction.setTranslation(translation);
            } else {
                transaction.setDescription(description);
            }

        });

        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDTO> uploadFile(String filePath) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));

            return readOFXFile(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(ErrorCode.ERR_901.getCode());
        }
    }

    private List<TransactionDTO> readOFXFile(InputStream file) {
        try {
            AggregateUnmarshaller<ResponseEnvelope> a = new AggregateUnmarshaller<>(ResponseEnvelope.class);
            ResponseEnvelope re = a.unmarshal(file);

            ResponseMessageSet messages = re.getMessageSet(MessageSetType.banking);

            List<TransactionDTO> transactions = new ArrayList<>();

            if (messages != null) {
                List<BankStatementResponseTransaction> banks = ((BankingResponseMessageSet) messages)
                        .getStatementResponses();

                banks.forEach(bank -> bank.getMessage().getTransactionList().getTransactions().forEach(transaction -> {
                    BigDecimal amount = transaction.getBigDecimalAmount();
                    Date datePosted = transaction.getDatePosted();
                    String memo = transaction.getMemo();

                    Translation translation = translationService.searchTranslation(memo);
                    LocalDateTime inputDate = datePosted.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                    transactions.add(new TransactionDTO(translation, memo, amount, inputDate));
                }));
            }

            return transactions;

        } catch (Exception e) {
            throw new RuntimeException(ErrorCode.ERR_902.getCode());
        }
    }
}
