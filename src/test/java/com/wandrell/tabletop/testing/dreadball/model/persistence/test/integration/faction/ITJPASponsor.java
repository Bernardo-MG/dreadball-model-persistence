package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.faction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPASponsor;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.SponsorTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPASponsor
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private SponsorTestRepository repository;

    public ITJPASponsor() {
        super();
    }

    @Test
    public void test_loads_groups() {
        final JPASponsor sponsor;

        sponsor = repository.findById(1);

        Assert.assertEquals(sponsor.getId(), (Integer) 1);
        Assert.assertEquals(sponsor.getSponsorName(), "sponsor1");

        Assert.assertEquals(sponsor.getAffinityGroups().size(), 2);
    }

    @Test
    public void test_loads_NoGroups() {
        final JPASponsor sponsor;

        sponsor = repository.findById(2);

        Assert.assertEquals(sponsor.getId(), (Integer) 2);
        Assert.assertEquals(sponsor.getSponsorName(), "sponsor2");

        Assert.assertEquals(sponsor.getAffinityGroups().size(), 0);
    }

}
