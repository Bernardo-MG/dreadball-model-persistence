package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.availability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.availability.team.JPATeamTypeAssetsAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamTypeAssetsAvailabilityRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPATeamTypeAssetsAvailability
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TeamTypeAssetsAvailabilityRepository repository;

    public ITJPATeamTypeAssetsAvailability() {
        super();
    }

    @Test
    public void test_loads() {
        final JPATeamTypeAssetsAvailability ava;

        ava = repository.findByTeamTypeId(1);

        Assert.assertEquals(ava.getDreadballCardCost(), (Integer) 2);
        Assert.assertEquals(ava.getCheerleadersCost(), (Integer) 3);
        Assert.assertEquals(ava.getCoachingStaffCost(), (Integer) 4);
        Assert.assertEquals(ava.getCoachingDieCost(), (Integer) 5);
        Assert.assertEquals(ava.getDreadballCardInitial(), (Integer) 6);
        Assert.assertEquals(ava.getCheerleadersInitial(), (Integer) 7);
        Assert.assertEquals(ava.getCoachingDieInitial(), (Integer) 8);
        Assert.assertEquals(ava.getDreadballCardMax(), (Integer) 9);
        Assert.assertEquals(ava.getCheerleadersMax(), (Integer) 10);
        Assert.assertEquals(ava.getCoachingDieMax(), (Integer) 11);
        Assert.assertEquals(ava.isStartingWithDefensiveCoachingStaff(),
                (Boolean) true);
        Assert.assertEquals(ava.isStartingWithOffensiveCoachingStaff(),
                (Boolean) false);
        Assert.assertEquals(ava.isStartingWithSupportCoachingStaff(),
                (Boolean) true);
    }

}
