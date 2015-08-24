package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAUnit;

public interface JPAUnitTestRepository
        extends UnitTestRepository, Repository<JPAUnit, Integer> {

    @Override
    @Query("SELECT unit FROM Unit unit WHERE unit.id = :id")
    public JPAUnit findById(@Param("id") int id);

}
