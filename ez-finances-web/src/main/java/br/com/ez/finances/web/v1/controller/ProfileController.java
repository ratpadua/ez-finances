package br.com.ez.finances.web.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ez.finances.api.v1.IProfileController;
import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.profile.CreateProfile;
import br.com.ez.finances.domain.form.profile.UpdateProfile;
import br.com.ez.finances.service.v1.IProfileService;
import br.com.ez.finances.web.v1.mapper.ProfileMapper;

/**
 * Profile related API controller implementation.
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
    public List<ProfileRepresentation> searchProfiles(@RequestParam(required = false) Status... statuses) {
        return mapper.toProfileRepresentation(profileService.searchProfiles(statuses));
    }

    @Override
    public ProfileRepresentation getProfile(@PathVariable Long id) {
        return mapper.toProfileRepresentation(profileService.getProfile(id));
    }

    @Override
    public ProfileRepresentation createProfile(@RequestBody @Valid CreateProfile createProfile) {
        return mapper.toProfileRepresentation(profileService.createProfile(createProfile));
    }

    @Override
    public ProfileRepresentation updateProfile(@PathVariable Long id, @RequestBody @Valid UpdateProfile updateProfile) {
        return mapper.toProfileRepresentation(profileService.updateProfile(id, updateProfile));
    }
}
