package br.com.ez.finances.service.v1.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.infrastructure.repository.ProfileRepository;
import br.com.ez.finances.service.v1.IProfileService;

/**
 * Created by raul.padua on 16/07/18
 */
@Service
public class ProfileService implements IProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile searchProfile(Long id) {
        return profileRepository.getOne(id);
    }

    @Override
    public Profile createProfile() {
        Profile profile = new Profile();
        profile.setName("teste");
        profile.setBalance(BigDecimal.ZERO);
        profile.setStatus(Status.ACTIVE);
        return profileRepository.save(profile);
    }
}
