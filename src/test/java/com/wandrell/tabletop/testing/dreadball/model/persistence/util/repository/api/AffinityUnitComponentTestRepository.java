package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAAffinityUnitComponent;

public interface AffinityUnitComponentTestRepository {

    public JPAAffinityUnitComponent findById(int id);

}
