package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.availability.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.availability.team.JPASponsorAssetsAvailability;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.SponsorAssetsAvailabilityRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPASponsorAssetsAvailability
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private SponsorAssetsAvailabilityRepository repository;

    public ITJPASponsorAssetsAvailability() {
        super();
    }

    @Test
    public void test_loads() {
        final JPASponsorAssetsAvailability ava;

        ava = repository.findById(1);

        Assert.assertEquals(ava.getAffinityGroupCost(), (Integer) 2);
        Assert.assertEquals(ava.getCheerleaderCost(), (Integer) 3);
        Assert.assertEquals(ava.getCheerleaderUnlockCost(), (Integer) 4);
        Assert.assertEquals(ava.getCoachingDieCost(), (Integer) 5);
        Assert.assertEquals(ava.getMediBotCost(), (Integer) 6);
        Assert.assertEquals(ava.getSabotageCardCost(), (Integer) 7);
        Assert.assertEquals(ava.getSpecialMovementCardCost(), (Integer) 8);
        Assert.assertEquals(ava.getWagerCost(), (Integer) 9);
        Assert.assertEquals(ava.getWagerMaxCount(), (Integer) 10);
        Assert.assertEquals(ava.getTeamCostMin(), (Integer) 11);
    }

}
