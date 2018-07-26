package br.com.ez.finances.service.v1.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ez.finances.domain.entity.Translation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.translation.CreateTranslation;
import br.com.ez.finances.domain.form.translation.SearchTranslation;
import br.com.ez.finances.domain.form.translation.UpdateTranslation;
import br.com.ez.finances.infrastructure.repository.TranslationRepository;
import br.com.ez.finances.service.v1.ITranslationService;

/**
 * Implementation of the translation related operations.
 */
@Service
public class TranslationService implements ITranslationService {

    private TranslationRepository translationRepository;

    @Autowired
    public TranslationService(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
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
        return translationRepository.save(Translation.of(createTranslation));
    }

    @Override
    public Translation updateTranslation(Long id, UpdateTranslation updateTranslation) {
        Translation translation = translationRepository.getOne(id);

        updateTranslation.getToDescription().ifPresent(translation::setToDescription);
        updateTranslation.getStatus().ifPresent(translation::setStatus);

        return translationRepository.save(translation);
    }
}
