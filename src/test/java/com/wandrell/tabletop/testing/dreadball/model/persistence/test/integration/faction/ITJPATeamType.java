package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.faction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamType;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamTypeTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPATeamType
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TeamTypeTestRepository repository;

    public ITJPATeamType() {
        super();
    }

    @Test
    public void test_loads_noRules() {
        final JPATeamType team;

        team = repository.findById(1);

        Assert.assertEquals(team.getId(), (Integer) 1);
        Assert.assertEquals(team.getTeamTypeName(), "team_type1");
    }

    @Test
    public void test_loads_rules() {
        final JPATeamType team;

        team = repository.findById(2);

        Assert.assertEquals(team.getId(), (Integer) 2);
        Assert.assertEquals(team.getTeamTypeName(), "team_type2");
    }

}
