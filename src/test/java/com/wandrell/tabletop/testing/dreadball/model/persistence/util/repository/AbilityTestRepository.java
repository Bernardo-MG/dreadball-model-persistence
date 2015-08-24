package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository;

import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;

public interface AbilityTestRepository {

    public JPAAbility findById(int id);

}
