package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPACompositeAffinityUnit;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.CompositeAffinityUnitTestRepository;

public interface JPACompositeAffinityUnitTestRepository
        extends CompositeAffinityUnitTestRepository,
        Repository<JPACompositeAffinityUnit, Integer> {

    @Override
    @Query("SELECT unit FROM CompositeAffinityUnit unit WHERE unit.id = :id")
    public JPACompositeAffinityUnit findById(@Param("id") int id);

}
