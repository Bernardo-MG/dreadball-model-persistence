package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPATeamTypeMVPAvailability;

public interface TeamTypeMVPAvailabilityRepository {

    public JPATeamTypeMVPAvailability findById(int id);

}
