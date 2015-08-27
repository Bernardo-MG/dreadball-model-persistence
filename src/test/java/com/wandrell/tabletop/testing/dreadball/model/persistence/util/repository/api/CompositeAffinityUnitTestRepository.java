package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPACompositeAffinityUnit;

public interface CompositeAffinityUnitTestRepository {

    public JPACompositeAffinityUnit findById(int id);

}
