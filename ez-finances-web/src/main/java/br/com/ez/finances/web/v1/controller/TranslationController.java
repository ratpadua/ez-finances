package br.com.ez.finances.web.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ez.finances.api.v1.ITranslationController;
import br.com.ez.finances.api.v1.representation.profile.ProfileRepresentation;
import br.com.ez.finances.api.v1.representation.translation.TranslationRepresentation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.profile.CreateProfile;
import br.com.ez.finances.domain.form.profile.UpdateProfile;
import br.com.ez.finances.domain.form.translation.CreateTranslation;
import br.com.ez.finances.domain.form.translation.SearchTranslation;
import br.com.ez.finances.domain.form.translation.UpdateTranslation;
import br.com.ez.finances.service.v1.IProfileService;
import br.com.ez.finances.service.v1.ITranslationService;
import br.com.ez.finances.web.v1.mapper.ProfileMapper;
import br.com.ez.finances.web.v1.mapper.TranslationMapper;

/**
 * Translation related API controller implementation.
 */
@RestController
public class TranslationController implements ITranslationController {

    private ITranslationService translationService;

    private TranslationMapper mapper;

    @Autowired
    public TranslationController(ITranslationService translationService, TranslationMapper mapper) {
        this.translationService = translationService;
        this.mapper = mapper;
    }

    @Override
    public List<TranslationRepresentation> getTranslations(@RequestParam Long profileId,
            @RequestParam(required = false) Status... statuses) {
        return mapper.toTranslationRepresentation(translationService.getTranslations(profileId, statuses));
    }

    @Override
    public TranslationRepresentation searchTranslation(@RequestParam String description) {
        return mapper.toTranslationRepresentation(translationService.searchTranslation(description));
    }

    @Override
    public TranslationRepresentation createTranslation(@RequestBody @Valid CreateTranslation createTranslation) {
        return mapper.toTranslationRepresentation(translationService.createTranslation(createTranslation));
    }

    @Override
    public TranslationRepresentation updateTranslation(@PathVariable Long id,
            @RequestBody @Valid UpdateTranslation updateTranslation) {
        return mapper.toTranslationRepresentation(translationService.updateTranslation(id, updateTranslation));
    }
}
