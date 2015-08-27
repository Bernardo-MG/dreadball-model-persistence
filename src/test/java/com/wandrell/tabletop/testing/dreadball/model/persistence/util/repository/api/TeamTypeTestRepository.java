package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamType;

public interface TeamTypeTestRepository {

    public JPATeamType findById(int id);

}
