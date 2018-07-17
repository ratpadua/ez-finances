package br.com.ez.finances.web.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.ez.finances.api.v1.IProfileController;
import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;
import br.com.ez.finances.service.v1.IProfileService;
import br.com.ez.finances.web.v1.mapper.ProfileMapper;

/**
 * Created by raul.padua on 16/07/18
 */
@RestController
public class ProfileController implements IProfileController {

    private IProfileService profileService;

    private ProfileMapper mapper;

    @Autowired
    public ProfileController(IProfileService profileService, ProfileMapper mapper) {
        this.profileService = profileService;
        this.mapper = mapper;
    }

    @Override
    public ProfileRepresentation searchProfile(@PathVariable Long id) {
        return mapper.toProfileRepresentation(profileService.searchProfile(id));
    }

    @Override
    public ProfileRepresentation createProfile() {
        return mapper.toProfileRepresentation(profileService.createProfile());
    }
}
