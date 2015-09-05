package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPATeamTypeUnitAvailability;

public interface TeamTypeUnitAvailabilityRepository {

    public JPATeamTypeUnitAvailability findById(int id);

}
