package br.com.ez.finances.service.v1.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.entity.Source;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.domain.form.source.CreateSource;
import br.com.ez.finances.domain.form.source.UpdateSource;
import br.com.ez.finances.infrastructure.exception.InvalidProfileException;
import br.com.ez.finances.infrastructure.exception.NotFoundException;
import br.com.ez.finances.infrastructure.repository.SourceRepository;
import br.com.ez.finances.service.v1.IProfileService;
import br.com.ez.finances.service.v1.ISourceService;

/**
 * Implementation of the source related operations.
 */
@Service
public class SourceService implements ISourceService {

    private SourceRepository sourceRepository;

    private IProfileService profileService;

    @Autowired
    public SourceService(SourceRepository sourceRepository, IProfileService profileService) {
        this.sourceRepository = sourceRepository;
        this.profileService = profileService;
    }

    @Override
    public List<Source> searchSources(Long profileId, Status... statuses) {
        statuses = Status.validateStatuses(statuses);

        Profile profile = profileService.getProfile(profileId);

        return sourceRepository.findByProfileEqualsAndStatusInOrderByName(profile, statuses);
    }

    @Override
    public Source getSource(Long profileId, Long id) {
        Optional<Source> optSource = sourceRepository.findById(id);

        if (!optSource.isPresent()) throw new NotFoundException(ErrorCode.ERR_700);

        if (!profileId.equals(optSource.get().getProfile().getId()))
            throw new InvalidProfileException(ErrorCode.ERR_710);

        return optSource.get();
    }

    @Override
    @Transactional
    public Source createSource(Long profileId, CreateSource createSource) {
        Profile profile = profileService.getProfile(profileId);
        return sourceRepository.save(Source.of(createSource, profile));
    }

    @Override
    @Transactional
    public Source updateSource(Long profileId, Long id, UpdateSource updateSource) {
        Source source = getSource(profileId, id);

        updateSource.getName().ifPresent(source::setName);
        updateSource.getStatus().ifPresent(source::setStatus);

        return sourceRepository.save(source);
    }
}
