package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityUnit;

public interface AffinityUnitTestRepository {

    public JPAAffinityUnit findById(int id);

}
