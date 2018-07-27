package br.com.ez.finances.service.v1.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ez.finances.domain.entity.Source;
import br.com.ez.finances.domain.entity.Translation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.domain.form.translation.CreateTranslation;
import br.com.ez.finances.domain.form.translation.UpdateTranslation;
import br.com.ez.finances.infrastructure.repository.TranslationRepository;
import br.com.ez.finances.service.v1.ISourceService;
import br.com.ez.finances.service.v1.ITranslationService;

/**
 * Implementation of the translation related operations.
 */
@Service
public class TranslationService implements ITranslationService {

    private TranslationRepository translationRepository;

    private ISourceService sourceService;

    @Autowired
    public TranslationService(TranslationRepository translationRepository, ISourceService sourceService) {
        this.translationRepository = translationRepository;
        this.sourceService = sourceService;
    }

    @Override
    public List<Translation> getTranslations(Status... statuses) {
        statuses = Status.validateStatuses(statuses);
        return translationRepository.findByStatusInOrderByDescription(statuses);
    }

    @Override
    public Translation searchTranslation(String description) {
        return translationRepository.findByDescriptionEquals(description);
    }

    @Override
    public Translation createTranslation(CreateTranslation createTranslation) {
        Source source = sourceService.searchSource(createTranslation.getSourceId());
        return translationRepository.save(Translation.of(createTranslation, source));
    }

    @Override
    public Translation updateTranslation(Long id, UpdateTranslation updateTranslation) {
        Optional<Translation> optTranslation = translationRepository.findById(id);

        if (!optTranslation.isPresent()) throw new RuntimeException(ErrorCode.ERR_800.getCode());

        Translation translation = optTranslation.get();

        updateTranslation.getToDescription().ifPresent(translation::setToDescription);
        updateTranslation.getStatus().ifPresent(translation::setStatus);
        updateTranslation.getSourceId().ifPresent(sourceId -> {
            Source source = sourceService.searchSource(sourceId);
            translation.setSource(source);
        });

        return translationRepository.save(translation);
    }
}
