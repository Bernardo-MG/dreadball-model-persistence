package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.unit.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPACompositeAffinityUnit;
import com.wandrell.tabletop.dreadball.model.unit.TeamPosition;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.CompositeAffinityUnitTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPACompositeAffinityUnit
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private CompositeAffinityUnitTestRepository repository;

    public ITJPACompositeAffinityUnit() {
        super();
    }

    @Test
    public void test_loads_guard() {
        final JPACompositeAffinityUnit unit;

        unit = repository.findById(3);

        Assert.assertEquals(unit.getId(), (Integer) 3);
        Assert.assertEquals(unit.getTemplateName(), "caffunit3");

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.GUARD);

        Assert.assertEquals(unit.isGiant(), (Boolean) false);

        Assert.assertEquals(unit.getAbilities().size(), 0);

        Assert.assertEquals(unit.getAffinityGroups().size(), 1);

        Assert.assertEquals(unit.getComponents().size(), 2);
    }

    @Test
    public void test_loads_jack() {
        final JPACompositeAffinityUnit unit;

        unit = repository.findById(2);

        Assert.assertEquals(unit.getId(), (Integer) 2);
        Assert.assertEquals(unit.getTemplateName(), "caffunit2");

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.JACK);

        Assert.assertEquals(unit.isGiant(), (Boolean) true);

        Assert.assertEquals(unit.getAbilities().size(), 2);

        Assert.assertEquals(unit.getAffinityGroups().size(), 1);

        Assert.assertEquals(unit.getComponents().size(), 1);
    }

    @Test
    public void test_loads_striker() {
        final JPACompositeAffinityUnit unit;

        unit = repository.findById(1);

        Assert.assertEquals(unit.getId(), (Integer) 1);
        Assert.assertEquals(unit.getTemplateName(), "caffunit1");

        Assert.assertEquals(unit.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(unit.getAttributes().getMovement(), (Integer) 2);
        Assert.assertEquals(unit.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(unit.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(unit.getAttributes().getStrength(), (Integer) 5);

        Assert.assertEquals(unit.getPosition(), TeamPosition.STRIKER);

        Assert.assertEquals(unit.isGiant(), (Boolean) false);

        Assert.assertEquals(unit.getAbilities().size(), 1);

        Assert.assertEquals(unit.getAffinityGroups().size(), 2);

        Assert.assertEquals(unit.getComponents().size(), 1);
    }

}
