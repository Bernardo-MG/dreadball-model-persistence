package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.unit.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.AbilityTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPAAbility
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private AbilityTestRepository repository;

    public ITJPAAbility() {
        super();
    }

    @Test
    public void test_loads() {
        final JPAAbility ability;

        ability = repository.findById(2);

        Assert.assertEquals(ability.getId(), (Integer) 2);
        Assert.assertEquals(ability.getAbilityName(), "ability2");
    }

}
