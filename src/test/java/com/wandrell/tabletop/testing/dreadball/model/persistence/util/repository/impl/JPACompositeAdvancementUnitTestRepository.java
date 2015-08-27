package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPACompositeAdvancementUnit;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.CompositeAdvancementUnitTestRepository;

public interface JPACompositeAdvancementUnitTestRepository
        extends CompositeAdvancementUnitTestRepository,
        Repository<JPACompositeAdvancementUnit, Integer> {

    @Override
    @Query("SELECT unit FROM CompositeAdvancementUnit unit WHERE unit.id = :id")
    public JPACompositeAdvancementUnit findById(@Param("id") int id);

}
