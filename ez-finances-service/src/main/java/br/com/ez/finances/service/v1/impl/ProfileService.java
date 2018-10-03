package br.com.ez.finances.service.v1.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.domain.form.profile.CreateProfile;
import br.com.ez.finances.domain.form.profile.UpdateProfile;
import br.com.ez.finances.infrastructure.exception.NotFoundException;
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
    public List<Profile> searchProfiles(Status... statuses) {
        statuses = Status.validateStatuses(statuses);
        return profileRepository.findByStatusInOrderByName(statuses);
    }

    @Override
    public Profile getProfile(Long id) {
        Optional<Profile> optProfile = profileRepository.findById(id);

        if(!optProfile.isPresent()) throw new NotFoundException(ErrorCode.ERR_600);

        return optProfile.get();
    }

    @Override
    @Transactional
    public Profile createProfile(CreateProfile createProfile) {
        return profileRepository.save(Profile.of(createProfile));
    }

    @Override
    @Transactional
    public Profile updateProfile(Long id, UpdateProfile updateProfile) {
        Profile profile = getProfile(id);

        updateProfile.getName().ifPresent(profile::setName);
        updateProfile.getBalance().ifPresent(profile::setBalance);
        updateProfile.getStatus().ifPresent(profile::setStatus);

        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public Profile addBalance(Long id, BigDecimal balance) {
        Profile profile = getProfile(id);
        profile.setBalance(profile.getBalance().add(balance));
        return profileRepository.save(profile);
    }
}
