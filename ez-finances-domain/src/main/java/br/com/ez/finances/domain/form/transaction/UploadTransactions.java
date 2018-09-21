package br.com.ez.finances.domain.form.transaction;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to create a new transaction.
 */
public class UploadTransactions {

    @NotNull
    @Valid
    private List<CreateTransaction> createTransactions;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields are
     * set as Optional.empty and later set using the setter.
     *
     * @param createTransactions Create transactions list.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private UploadTransactions(@JsonProperty("createTransactions") List<CreateTransaction> createTransactions) {
        this.createTransactions = createTransactions;
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     *
     * @param createTransactions Mandatory parameter create transactions list.
     * @return Upload transactions valid form.
     */
    public static UploadTransactions of(List<CreateTransaction> createTransactions) {
        UploadTransactions createTransaction = new UploadTransactions(createTransactions);

        ObjectValidator.validate(createTransaction);

        return createTransaction;
    }

    public List<CreateTransaction> getCreateTransactions() {
        return createTransactions;
    }
}
