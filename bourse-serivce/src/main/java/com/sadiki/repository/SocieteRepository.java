package com.sadiki.repository;

import com.sadiki.entities.Societe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by X-MART on 6/28/2017.
 */
@RepositoryRestResource
public interface SocieteRepository extends JpaRepository<Societe,Long> {
    public Page<Societe> findByNomSocieteContaining(@Param("nomSociete") String nomSociete, Pageable pageable);
}
