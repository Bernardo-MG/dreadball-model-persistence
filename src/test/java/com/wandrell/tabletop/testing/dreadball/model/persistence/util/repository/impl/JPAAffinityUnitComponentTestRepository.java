package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAAffinityUnitComponent;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.AffinityUnitComponentTestRepository;

public interface JPAAffinityUnitComponentTestRepository
        extends AffinityUnitComponentTestRepository,
        Repository<JPAAffinityUnitComponent, Integer> {

    @Override
    @Query("SELECT component FROM AffinityUnitComponent component WHERE component.id = :id")
    public JPAAffinityUnitComponent findById(@Param("id") int id);

}
