package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.faction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamRule;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.TeamRuleTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPATeamRule
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TeamRuleTestRepository repository;

    public ITJPATeamRule() {
        super();
    }

    @Test
    public void test_loads() {
        final JPATeamRule rule;

        rule = repository.findById(1);

        Assert.assertEquals(rule.getId(), (Integer) 1);
        Assert.assertEquals(rule.getTeamRuleName(), "team_rule1");
    }

}
