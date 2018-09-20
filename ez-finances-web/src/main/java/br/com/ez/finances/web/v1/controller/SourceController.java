package br.com.ez.finances.web.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ez.finances.api.v1.ISourceController;
import br.com.ez.finances.api.v1.representation.source.SourceRepresentation;
import br.com.ez.finances.domain.enums.Status;
import br.com.ez.finances.domain.form.source.CreateSource;
import br.com.ez.finances.domain.form.source.UpdateSource;
import br.com.ez.finances.service.v1.ISourceService;
import br.com.ez.finances.web.v1.mapper.SourceMapper;

/**
 * Source related API controller implementation.
 */
@RestController
public class SourceController implements ISourceController {

    private ISourceService sourceService;

    private SourceMapper mapper;

    @Autowired
    public SourceController(ISourceService sourceService, SourceMapper mapper) {
        this.sourceService = sourceService;
        this.mapper = mapper;
    }

    @Override
    public List<SourceRepresentation> getSources(@RequestHeader("Profile-Id") Long profileId,
            @RequestParam(required = false) Status... statuses) {
        return mapper.toSourceRepresentation(sourceService.getSources(profileId, statuses));
    }

    @Override
    public SourceRepresentation searchSource(@RequestHeader("Profile-Id") Long profileId, @PathVariable Long id) {
        return mapper.toSourceRepresentation(sourceService.searchSource(profileId, id));
    }

    @Override
    public SourceRepresentation createSource(@RequestHeader("Profile-Id") Long profileId,
            @RequestBody @Valid CreateSource createSource) {
        return mapper.toSourceRepresentation(sourceService.createSource(profileId, createSource));
    }

    @Override
    public SourceRepresentation updateSource(@RequestHeader("Profile-Id") Long profileId,
            @PathVariable Long id, @RequestBody @Valid UpdateSource updateSource) {
        return mapper.toSourceRepresentation(sourceService.updateSource(profileId, id, updateSource));
    }
}
