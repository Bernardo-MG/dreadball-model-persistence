package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAUnit;

public interface UnitTestRepository {

    public JPAUnit findById(int id);

}
