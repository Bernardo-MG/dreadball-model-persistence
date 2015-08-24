package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;

public interface AffinityGroupTestRepository {

    public JPAAffinityGroup findById(int id);

}
