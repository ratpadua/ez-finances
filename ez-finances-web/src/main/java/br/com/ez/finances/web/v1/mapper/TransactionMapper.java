package br.com.ez.finances.web.v1.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.ez.finances.api.v1.representation.transaction.TransactionRepresentation;
import br.com.ez.finances.api.v1.representation.translation.TranslationRepresentation;
import br.com.ez.finances.domain.entity.Transaction;
import br.com.ez.finances.domain.entity.Translation;

/**
 * Translation related mapper utility. Uses Mapstruct Framework to generate implementation.
 */
@Mapper(componentModel = "spring", uses = {TranslationMapper.class})
public interface TransactionMapper {

    /**
     * Converts the Transaction entity into a rest response representation of it.
     *
     * @param transaction The transaction entity.
     * @return A rest response representation of a transaction.
     */
    TransactionRepresentation toTransactionRepresentation(Transaction transaction);

    /**
     * Converts a list of Transaction entities into a rest response representation list of it.
     *
     * @param transactions The transaction entities.
     * @return A rest response representation of a list of transactions.
     */
    List<TransactionRepresentation> toTransactionRepresentation(List<Transaction> transactions);

    /**
     * Converts a page of Transaction entities into a rest response representation list of it.
     *
     * @param transactions The transaction page.
     * @return A rest response representation of a page of transactions.
     */
    default Page<TransactionRepresentation> toTransactionRepresentation(Page<Transaction> transactions) {
        List<TransactionRepresentation> content = toTransactionRepresentation(transactions.getContent());
        PageRequest pageRequest = PageRequest.of(transactions.getNumber(), transactions.getSize(), transactions.getSort());
        return new PageImpl<>(content, pageRequest, transactions.getTotalElements());
    }
}
