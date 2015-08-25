package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.AffinityGroupTestRepository;

public interface JPAAffinityGroupTestRepository extends
        AffinityGroupTestRepository, Repository<JPAAffinityGroup, Integer> {

    @Override
    @Query("SELECT group FROM AffinityGroup group WHERE group.id = :id")
    public JPAAffinityGroup findById(@Param("id") int id);

}
