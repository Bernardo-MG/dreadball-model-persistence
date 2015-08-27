package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamType;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamTypeTestRepository;

public interface JPATeamTypeTestRepository
        extends TeamTypeTestRepository, Repository<JPATeamType, Integer> {

    @Override
    @Query("SELECT team FROM TeamType team WHERE team.id = :id")
    public JPATeamType findById(@Param("id") int id);

}
