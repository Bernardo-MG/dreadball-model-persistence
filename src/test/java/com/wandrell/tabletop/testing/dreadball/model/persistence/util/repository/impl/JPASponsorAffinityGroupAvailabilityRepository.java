package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPASponsorAffinityGroupAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.SponsorAffinityGroupAvailabilityRepository;

public interface JPASponsorAffinityGroupAvailabilityRepository
        extends SponsorAffinityGroupAvailabilityRepository,
        Repository<JPASponsorAffinityGroupAvailability, Integer> {

    @Override
    @Query("SELECT ava FROM SponsorAffinityGroupAvailability ava WHERE ava.id = :id")
    public JPASponsorAffinityGroupAvailability findById(@Param("id") int id);

}
