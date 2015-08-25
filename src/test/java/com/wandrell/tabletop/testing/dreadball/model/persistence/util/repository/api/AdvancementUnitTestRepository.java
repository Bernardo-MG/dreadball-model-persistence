package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAdvancementUnit;

public interface AdvancementUnitTestRepository {

    public JPAAdvancementUnit findById(int id);

}
