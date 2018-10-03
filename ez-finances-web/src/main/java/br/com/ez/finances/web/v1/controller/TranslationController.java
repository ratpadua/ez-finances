package br.com.ez.finances.web.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ez.finances.api.v1.ITranslationController;
import br.com.ez.finances.api.v1.representation.translation.TranslationRepresentation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.translation.CreateTranslation;
import br.com.ez.finances.domain.form.translation.UpdateTranslation;
import br.com.ez.finances.service.v1.ITranslationService;
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
    public List<TranslationRepresentation> searchTranslations(@RequestHeader("Profile-Id") Long profileId,
            @RequestParam(required = false) Status... statuses) {
        return mapper.toTranslationRepresentation(translationService.searchTranslations(profileId, statuses));
    }

    @Override
    public TranslationRepresentation getTranslation(@RequestHeader("Profile-Id") Long profileId,
            @PathVariable Long id) {
        return mapper.toTranslationRepresentation(translationService.getTranslation(profileId, id));
    }

    @Override
    public TranslationRepresentation searchTranslationByDescription(@RequestHeader("Profile-Id") Long profileId,
            @RequestParam String description) {
        return mapper.toTranslationRepresentation(
                translationService.searchTranslationByDescription(profileId, description));
    }

    @Override
    public TranslationRepresentation createTranslation(@RequestHeader("Profile-Id") Long profileId,
            @RequestBody @Valid CreateTranslation createTranslation) {
        return mapper.toTranslationRepresentation(translationService.createTranslation(profileId, createTranslation));
    }

    @Override
    public TranslationRepresentation updateTranslation(@RequestHeader("Profile-Id") Long profileId,
            @PathVariable Long id, @RequestBody @Valid UpdateTranslation updateTranslation) {
        return mapper.toTranslationRepresentation(
                translationService.updateTranslation(profileId, id, updateTranslation));
    }
}
