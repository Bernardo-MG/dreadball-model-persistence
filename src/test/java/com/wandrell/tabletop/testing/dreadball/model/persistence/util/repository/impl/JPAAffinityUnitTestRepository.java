package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityUnit;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.AffinityUnitTestRepository;

public interface JPAAffinityUnitTestRepository extends
        AffinityUnitTestRepository, Repository<JPAAffinityUnit, Integer> {

    @Override
    @Query("SELECT unit FROM AffinityUnit unit WHERE unit.id = :id")
    public JPAAffinityUnit findById(@Param("id") int id);

}
