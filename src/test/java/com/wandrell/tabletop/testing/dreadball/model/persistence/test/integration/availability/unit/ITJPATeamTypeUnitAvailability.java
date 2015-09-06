package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.availability.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPATeamTypeUnitAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamTypeUnitAvailabilityRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPATeamTypeUnitAvailability
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TeamTypeUnitAvailabilityRepository repository;

    public ITJPATeamTypeUnitAvailability() {
        super();
    }

    @Test
    public void test_loads() {
        final JPATeamTypeUnitAvailability ava;

        ava = repository.findById(1);

        Assert.assertEquals(ava.getInitialNumber(), (Integer) 5);
        Assert.assertEquals(ava.getMaxNumber(), (Integer) 10);
        Assert.assertEquals(ava.getTeamType().getTeamTypeName(), "team_type1");
        Assert.assertEquals(ava.getUnit().getTemplateName(), "unit2");
    }

}
