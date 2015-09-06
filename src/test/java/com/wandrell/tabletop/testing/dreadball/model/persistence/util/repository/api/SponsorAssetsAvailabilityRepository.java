package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.availability.team.JPASponsorAssetsAvailability;

public interface SponsorAssetsAvailabilityRepository {

    public JPASponsorAssetsAvailability findById(int id);

}
