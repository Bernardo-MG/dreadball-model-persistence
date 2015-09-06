package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.availability.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPATeamTypeMVPAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamTypeMVPAvailabilityRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPATeamTypeMVPAvailability
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TeamTypeMVPAvailabilityRepository repository;

    public ITJPATeamTypeMVPAvailability() {
        super();
    }

    @Test
    public void test_loads() {
        final JPATeamTypeMVPAvailability ava;

        ava = repository.findById(1);

        Assert.assertEquals(ava.getTeamType().getTeamTypeName(), "team_type1");
        Assert.assertEquals(ava.getUnit().getTemplateName(), "unit2");
    }

}
