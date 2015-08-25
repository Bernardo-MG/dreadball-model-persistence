package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAdvancementUnit;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.AdvancementUnitTestRepository;

public interface JPAAdvancementUnitTestRepository extends
        AdvancementUnitTestRepository, Repository<JPAAdvancementUnit, Integer> {

    @Override
    @Query("SELECT unit FROM AdvancementUnit unit WHERE unit.id = :id")
    public JPAAdvancementUnit findById(@Param("id") int id);

}
