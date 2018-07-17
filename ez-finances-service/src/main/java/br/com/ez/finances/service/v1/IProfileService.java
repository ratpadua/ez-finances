package br.com.ez.finances.service.v1;

import br.com.ez.finances.domain.entity.Profile;

/**
 * Created by raul.padua on 16/07/18
 */
public interface IProfileService {

    Profile searchProfile(Long id);

    Profile createProfile();
}
