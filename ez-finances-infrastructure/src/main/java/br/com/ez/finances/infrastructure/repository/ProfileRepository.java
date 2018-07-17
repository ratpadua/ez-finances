package br.com.ez.finances.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ez.finances.domain.entity.Profile;

/**
 * Created by raul.padua on 17/07/18
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
