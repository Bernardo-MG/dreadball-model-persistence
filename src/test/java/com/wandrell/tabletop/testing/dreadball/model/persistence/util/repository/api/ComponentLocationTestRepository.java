package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAComponentLocation;

public interface ComponentLocationTestRepository {

    public JPAComponentLocation findById(int id);

}
