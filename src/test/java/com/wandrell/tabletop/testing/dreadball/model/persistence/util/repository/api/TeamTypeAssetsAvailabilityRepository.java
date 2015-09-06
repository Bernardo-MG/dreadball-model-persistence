package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.availability.team.JPATeamTypeAssetsAvailability;

public interface TeamTypeAssetsAvailabilityRepository {

    public JPATeamTypeAssetsAvailability findByTeamTypeId(int id);

}
