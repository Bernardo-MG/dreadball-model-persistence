package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAComponentLocation;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.ComponentLocationTestRepository;

public interface JPAComponentLocationTestRepository
        extends ComponentLocationTestRepository,
        Repository<JPAComponentLocation, Integer> {

    @Override
    @Query("SELECT location FROM ComponentLocation location WHERE location.id = :id")
    public JPAComponentLocation findById(@Param("id") int id);

}
