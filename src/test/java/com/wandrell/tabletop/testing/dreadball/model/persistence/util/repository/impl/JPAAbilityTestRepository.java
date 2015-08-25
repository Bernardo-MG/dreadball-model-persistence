package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.AbilityTestRepository;

public interface JPAAbilityTestRepository
        extends AbilityTestRepository, Repository<JPAAbility, Integer> {

    @Override
    @Query("SELECT ability FROM Ability ability WHERE ability.id = :id")
    public JPAAbility findById(@Param("id") int id);

}
