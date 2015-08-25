package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAUnitComponent;

public interface UnitComponentTestRepository {

    public JPAUnitComponent findById(int id);

}
