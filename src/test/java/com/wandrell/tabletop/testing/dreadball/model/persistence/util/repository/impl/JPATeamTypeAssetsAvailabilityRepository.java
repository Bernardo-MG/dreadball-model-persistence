package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.availability.team.JPATeamTypeAssetsAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamTypeAssetsAvailabilityRepository;

public interface JPATeamTypeAssetsAvailabilityRepository
        extends TeamTypeAssetsAvailabilityRepository,
        Repository<JPATeamTypeAssetsAvailability, Integer> {

    @Override
    @Query("SELECT ava FROM TeamTypeAssetsAvailability ava WHERE ava.teamType.id = :id")
    public JPATeamTypeAssetsAvailability findByTeamTypeId(@Param("id") int id);

}
