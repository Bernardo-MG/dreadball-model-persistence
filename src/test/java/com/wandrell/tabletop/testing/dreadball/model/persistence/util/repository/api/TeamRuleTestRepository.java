package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamRule;

public interface TeamRuleTestRepository {

    public JPATeamRule findById(int id);

}
