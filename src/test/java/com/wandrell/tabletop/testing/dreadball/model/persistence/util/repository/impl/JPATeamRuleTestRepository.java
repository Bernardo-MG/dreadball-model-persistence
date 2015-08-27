package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamRule;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamRuleTestRepository;

public interface JPATeamRuleTestRepository
        extends TeamRuleTestRepository, Repository<JPATeamRule, Integer> {

    @Override
    @Query("SELECT rule FROM TeamRule rule WHERE rule.id = :id")
    public JPATeamRule findById(@Param("id") int id);

}
