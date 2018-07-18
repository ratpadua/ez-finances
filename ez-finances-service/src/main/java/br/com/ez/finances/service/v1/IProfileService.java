package br.com.ez.finances.service.v1;

import java.math.BigDecimal;
import java.util.List;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.profile.CreateProfile;
import br.com.ez.finances.domain.form.profile.UpdateProfile;

/**
 * Interface containing Profile related operations.
 */
public interface IProfileService {

    /**
     * Searches all profiles with the provided statuses, if none is provided, all profiles are searched.
     * @param statuses Optional parameter containing one or more valid statuses (ACTIVE, INACTIVE).
     * @return A list with all the profiles found.
     */
    List<Profile> getProfiles(Status... statuses);

    /**
     * Searches the profile with the provided id.
     * @param id Mandatory variable with the id of the profile.
     * @return The profile found with the provided id.
     */
    Profile searchProfile(Long id);

    /**
     * Creates a new profile using the values provided on the form object.
     * @param createProfile Mandatory valid object with the new profile information.
     * @return The new profile created.
     */
    Profile createProfile(CreateProfile createProfile);

    /**
     * Updates the profile with the provided id with the information provided on the form object.
     * @param id Mandatory variable with the id of the profile.
     * @param updateProfile Mandatory valid object with the values to be updated.
     * @return The updated profile.
     */
    Profile updateProfile(Long id, UpdateProfile updateProfile);

    /**
     * Adds balance (positive or negative) to the profile with the provided id.
     * @param id Mandatory variable with the id of the profile.
     * @param balance Mandatory variable with the balance to be added.
     * @return The profile with updated balance.
     */
    Profile addBalance(Long id, BigDecimal balance);
}
