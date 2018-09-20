package br.com.ez.finances.service.v1.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.entity.Source;
import br.com.ez.finances.domain.entity.Translation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.domain.form.translation.CreateTranslation;
import br.com.ez.finances.domain.form.translation.UpdateTranslation;
import br.com.ez.finances.infrastructure.exception.InvalidProfileException;
import br.com.ez.finances.infrastructure.exception.NotFoundException;
import br.com.ez.finances.infrastructure.repository.TranslationRepository;
import br.com.ez.finances.service.v1.IProfileService;
import br.com.ez.finances.service.v1.ISourceService;
import br.com.ez.finances.service.v1.ITranslationService;

/**
 * Implementation of the translation related operations.
 */
@Service
public class TranslationService implements ITranslationService {

    private TranslationRepository translationRepository;

    private IProfileService profileService;

    private ISourceService sourceService;

    @Autowired
    public TranslationService(TranslationRepository translationRepository, IProfileService profileService,
            ISourceService sourceService) {
        this.translationRepository = translationRepository;
        this.profileService = profileService;
        this.sourceService = sourceService;
    }

    @Override
    public List<Translation> getTranslations(Long profileId, Status... statuses) {
        statuses = Status.validateStatuses(statuses);

        Profile profile = profileService.searchProfile(profileId);

        return translationRepository.findByProfileEqualsAndStatusInOrderByDescription(profile, statuses);
    }

    @Override
    public Translation searchTranslation(Long profileId, Long id) {
        Optional<Translation> optTranslation = translationRepository.findById(id);

        if (!optTranslation.isPresent()) throw new NotFoundException(ErrorCode.ERR_800);

        if (!profileId.equals(optTranslation.get().getProfile().getId()))
            throw new InvalidProfileException(ErrorCode.ERR_810);

        return optTranslation.get();
    }

    @Override
    public Translation searchTranslationByDescription(Long profileId, String description) {
        Profile profile = profileService.searchProfile(profileId);
        return translationRepository.findByProfileEqualsAndDescriptionEquals(profile, description);
    }

    @Override
    public Translation createTranslation(Long profileId, CreateTranslation createTranslation) {
        Translation translation;

        Profile profile = profileService.searchProfile(profileId);

        if (createTranslation.getSourceId().isPresent()) {
            Source source = sourceService.searchSource(profileId, createTranslation.getSourceId().get());
            translation = Translation.of(createTranslation, profile, source);
        } else {
            translation = Translation.of(createTranslation, profile, null);
        }

        return translationRepository.save(translation);
    }

    @Override
    public Translation updateTranslation(Long profileId, Long id, UpdateTranslation updateTranslation) {
        Translation translation = searchTranslation(profileId, id);

        updateTranslation.getToDescription().ifPresent(translation::setToDescription);
        updateTranslation.getStatus().ifPresent(translation::setStatus);
        updateTranslation.getSourceId().ifPresent(sourceId -> {
            Source source = sourceService.searchSource(profileId, sourceId);
            translation.setSource(source);
        });

        return translationRepository.save(translation);
    }
}
