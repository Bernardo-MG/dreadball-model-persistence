package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAUnitComponent;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.UnitComponentTestRepository;

public interface JPAUnitComponentTestRepository extends
        UnitComponentTestRepository, Repository<JPAUnitComponent, Integer> {

    @Override
    @Query("SELECT component FROM UnitComponent component WHERE component.id = :id")
    public JPAUnitComponent findById(@Param("id") int id);

}
