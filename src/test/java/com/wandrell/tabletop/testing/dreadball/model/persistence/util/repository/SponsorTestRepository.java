package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPASponsor;

public interface SponsorTestRepository {

    public JPASponsor findById(int id);

}
