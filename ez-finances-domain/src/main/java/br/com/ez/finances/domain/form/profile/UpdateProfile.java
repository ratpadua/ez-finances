package br.com.ez.finances.domain.form.profile;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to update a profile.
 */
public class UpdateProfile {

    private Optional<String> name;

    private Optional<BigDecimal> balance;

    private Optional<Status> status;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields
     * are set as Optional.empty and later set using the setter.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private UpdateProfile() {
        this.name = Optional.empty();
        this.balance = Optional.empty();
        this.status = Optional.empty();
    }

    /**
     * Static form builder with all parameters.
     * @param name Optional parameter profile name. If not set the name is not changed.
     * @param balance Optional parameter profile balance. If not set the balance is not changed.
     * @param status Optional parameter profile status. If not set the status is not changed.
     * @return Update profile valid form.
     */
    public static UpdateProfile of(String name, BigDecimal balance, Status status) {
        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.setName(name);
        updateProfile.setBalance(balance);
        updateProfile.setStatus(status);

        ObjectValidator.validate(updateProfile);

        return updateProfile;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public Optional<BigDecimal> getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = Optional.ofNullable(balance);
    }

    public Optional<Status> getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = Optional.ofNullable(status);
    }
}
