package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.availability.team.JPASponsorAssetsAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.SponsorAssetsAvailabilityRepository;

public interface JPASponsorAssetsAvailabilityRepository
        extends SponsorAssetsAvailabilityRepository,
        Repository<JPASponsorAssetsAvailability, Integer> {

    @Override
    @Query("SELECT ava FROM SponsorAssetsAvailability ava WHERE ava.id = :id")
    public JPASponsorAssetsAvailability findById(@Param("id") int id);

}
