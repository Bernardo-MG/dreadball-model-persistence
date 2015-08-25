package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAdvancementUnit;
import com.wandrell.tabletop.dreadball.model.unit.TeamPosition;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.AdvancementUnitTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPAAdvancementUnit
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private AdvancementUnitTestRepository repository;

    public ITJPAAdvancementUnit() {
        super();
    }

    @Test
    public void test_loads_guard() {
        final JPAAdvancementUnit unit;

        unit = repository.findById(3);

        Assert.assertEquals(unit.getId(), (Integer) 3);
        Assert.assertEquals(unit.getTemplateName(), "advunit3");
        Assert.assertEquals(unit.getCost(), (Integer) 11);

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.GUARD);

        Assert.assertEquals(unit.isGiant(), (Boolean) false);

        Assert.assertEquals(unit.getAbilities().size(), 0);

        Assert.assertEquals(unit.getUnspentExperience(), (Integer) 10);
        Assert.assertEquals(unit.getRank(), (Integer) 20);

        Assert.assertEquals(unit.getGraftedImplant().getComponentName(),
                "component1");
    }

    @Test
    public void test_loads_jack() {
        final JPAAdvancementUnit unit;

        unit = repository.findById(2);

        Assert.assertEquals(unit.getId(), (Integer) 2);
        Assert.assertEquals(unit.getTemplateName(), "advunit2");
        Assert.assertEquals(unit.getCost(), (Integer) 11);

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.JACK);

        Assert.assertEquals(unit.isGiant(), (Boolean) true);

        Assert.assertEquals(unit.getAbilities().size(), 2);

        Assert.assertEquals(unit.getUnspentExperience(), (Integer) 10);
        Assert.assertEquals(unit.getRank(), (Integer) 20);

        Assert.assertEquals(unit.getGraftedImplant().getComponentName(),
                "component1");
    }

    @Test
    public void test_loads_striker() {
        final JPAAdvancementUnit unit;

        unit = repository.findById(1);

        Assert.assertEquals(unit.getId(), (Integer) 1);
        Assert.assertEquals(unit.getTemplateName(), "advunit1");
        Assert.assertEquals(unit.getCost(), (Integer) 11);

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.STRIKER);

        Assert.assertEquals(unit.isGiant(), (Boolean) false);

        Assert.assertEquals(unit.getAbilities().size(), 1);

        Assert.assertEquals(unit.getUnspentExperience(), (Integer) 10);
        Assert.assertEquals(unit.getRank(), (Integer) 20);

        Assert.assertEquals(unit.getGraftedImplant().getComponentName(),
                "component1");
    }

}
