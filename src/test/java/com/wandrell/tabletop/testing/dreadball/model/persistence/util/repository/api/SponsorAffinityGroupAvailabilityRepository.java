package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPASponsorAffinityGroupAvailability;

public interface SponsorAffinityGroupAvailabilityRepository {

    public JPASponsorAffinityGroupAvailability findById(int id);

}
