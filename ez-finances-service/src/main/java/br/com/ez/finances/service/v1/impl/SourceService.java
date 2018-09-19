package br.com.ez.finances.service.v1.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ez.finances.domain.entity.Profile;
import br.com.ez.finances.domain.entity.Source;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.error.ErrorCode;
import br.com.ez.finances.domain.form.source.CreateSource;
import br.com.ez.finances.domain.form.source.UpdateSource;
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
    public List<Source> getSources(Long profileId, Status... statuses) {
        statuses = Status.validateStatuses(statuses);
        return sourceRepository.findByProfileIdEqualsAndStatusInOrderByName(profileId, statuses);
    }

    @Override
    public Source searchSource(Long id) {
        Optional<Source> optSource = sourceRepository.findById(id);

        if (!optSource.isPresent()) throw new NotFoundException(ErrorCode.ERR_700);

        return optSource.get();
    }

    @Override
    public Source createSource(CreateSource createSource) {
        Profile profile = profileService.searchProfile(createSource.getProfileId());
        return sourceRepository.save(Source.of(createSource, profile));
    }

    @Override
    public Source updateSource(Long id, UpdateSource updateSource) {
        Optional<Source> optSource = sourceRepository.findById(id);

        if (!optSource.isPresent()) throw new NotFoundException(ErrorCode.ERR_700);

        Source source = optSource.get();

        updateSource.getName().ifPresent(source::setName);
        updateSource.getStatus().ifPresent(source::setStatus);

        return sourceRepository.save(source);
    }
}
