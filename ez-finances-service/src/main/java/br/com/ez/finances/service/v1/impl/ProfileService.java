package br.com.ez.finances.service.v1.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.domain.form.profile.CreateProfile;
import br.com.ez.finances.domain.form.profile.UpdateProfile;
import br.com.ez.finances.infrastructure.repository.ProfileRepository;
import br.com.ez.finances.service.v1.IProfileService;

/**
 * Implementation of the profile related operations.
 */
@Service
public class ProfileService implements IProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> getProfiles(Status... statuses) {
        statuses = Status.validateStatuses(statuses);
        return profileRepository.findByStatusInOrderByName(statuses);
    }

    @Override
    public Profile searchProfile(Long id) {
        Optional<Profile> optProfile = profileRepository.findById(id);

        if(!optProfile.isPresent()) throw new RuntimeException(ErrorCode.ERR_600.getCode());

        return optProfile.get();
    }

    @Override
    public Profile createProfile(CreateProfile createProfile) {
        return profileRepository.save(Profile.of(createProfile));
    }

    @Override
    public Profile updateProfile(Long id, UpdateProfile updateProfile) {
        Optional<Profile> optProfile = profileRepository.findById(id);

        if (!optProfile.isPresent()) throw new RuntimeException(ErrorCode.ERR_600.getCode());

        Profile profile = optProfile.get();

        updateProfile.getName().ifPresent(profile::setName);
        updateProfile.getBalance().ifPresent(profile::setBalance);
        updateProfile.getStatus().ifPresent(profile::setStatus);

        return profileRepository.save(profile);
    }

    @Override
    public Profile addBalance(Long id, BigDecimal balance) {
        Optional<Profile> optProfile = profileRepository.findById(id);

        if (!optProfile.isPresent()) throw new RuntimeException(ErrorCode.ERR_600.getCode());

        Profile profile = optProfile.get();
        profile.setBalance(profile.getBalance().add(balance));
        return profileRepository.save(profile);
    }
}
