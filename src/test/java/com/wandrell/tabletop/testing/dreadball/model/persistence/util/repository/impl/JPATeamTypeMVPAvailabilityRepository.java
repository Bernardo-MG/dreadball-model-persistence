package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPATeamTypeMVPAvailability;
import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPATeamTypeUnitAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamTypeMVPAvailabilityRepository;

public interface JPATeamTypeMVPAvailabilityRepository
        extends TeamTypeMVPAvailabilityRepository,
        Repository<JPATeamTypeUnitAvailability, Integer> {

    @Override
    @Query("SELECT ava FROM TeamTypeMVPAvailability ava WHERE ava.id = :id")
    public JPATeamTypeMVPAvailability findById(@Param("id") int id);

}
