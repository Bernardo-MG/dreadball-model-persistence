package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPACompositeAdvancementUnit;

public interface CompositeAdvancementUnitTestRepository {

    public JPACompositeAdvancementUnit findById(int id);

}
