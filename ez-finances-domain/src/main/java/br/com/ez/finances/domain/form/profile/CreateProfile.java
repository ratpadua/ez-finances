package br.com.ez.finances.domain.form.profile;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ez.finances.domain.validator.ObjectValidator;

/**
 * Form containing the information to create a new profile.
 */
public class CreateProfile {

    @NotEmpty
    private String name;

    private Optional<BigDecimal> balance;

    /**
     * Constructor with the mandatory fields, used by Spring/Jackson to create the object. Any non-mandatory fields
     * are set as Optional.empty and later set using the setter.
     * @param name Name of the new profile.
     */
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    private CreateProfile(@JsonProperty("name") String name) {
        this.name = name;
        this.balance = Optional.empty();
    }

    /**
     * Static form builder requiring only the mandatory parameters.
     * @param name Mandatory parameter profile name.
     * @return Create profile valid form.
     */
    public static CreateProfile of(String name) {
        CreateProfile createProfile = new CreateProfile(name);

        ObjectValidator.validate(createProfile);

        return createProfile;
    }

    /**
     * Static form builder with all parameters.
     * @param name Mandatory parameter profile name.
     * @param balance Optional parameter profile balance.
     * @return Create profile valid form.
     */
    public static CreateProfile of(String name, BigDecimal balance) {
        CreateProfile createProfile = new CreateProfile(name);
        createProfile.setBalance(balance);

        ObjectValidator.validate(createProfile);

        return createProfile;
    }

    public String getName() {
        return name;
    }

    public Optional<BigDecimal> getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = Optional.ofNullable(balance);
    }
}
