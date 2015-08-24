package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAUnit;
import com.wandrell.tabletop.dreadball.model.unit.TeamPosition;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.UnitTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPAUnit
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UnitTestRepository repository;

    public ITJPAUnit() {
        super();
    }

    @Test
    public void test_loads_guard() {
        final JPAUnit template;

        template = repository.findById(3);

        Assert.assertEquals(template.getId(), (Integer) 3);
        Assert.assertEquals(template.getTemplateName(), "template3");

        Assert.assertEquals(template.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(template.getAttributes().getMovement(),
                (Integer) 2);
        Assert.assertEquals(template.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(template.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(template.getAttributes().getStrength(),
                (Integer) 5);

        Assert.assertEquals(template.getPosition(), TeamPosition.GUARD);

        Assert.assertEquals(template.isGiant(), (Boolean) false);

        Assert.assertEquals(template.getAbilities().size(), 0);
    }

    @Test
    public void test_loads_jack() {
        final JPAUnit template;

        template = repository.findById(2);

        Assert.assertEquals(template.getId(), (Integer) 2);
        Assert.assertEquals(template.getTemplateName(), "template2");

        Assert.assertEquals(template.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(template.getAttributes().getMovement(),
                (Integer) 2);
        Assert.assertEquals(template.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(template.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(template.getAttributes().getStrength(),
                (Integer) 5);

        Assert.assertEquals(template.getPosition(), TeamPosition.JACK);

        Assert.assertEquals(template.isGiant(), (Boolean) true);

        Assert.assertEquals(template.getAbilities().size(), 2);
    }

    @Test
    public void test_loads_striker() {
        final JPAUnit template;

        template = repository.findById(1);

        Assert.assertEquals(template.getId(), (Integer) 1);
        Assert.assertEquals(template.getTemplateName(), "template1");

        Assert.assertEquals(template.getAttributes().getArmor(), (Integer) 1);
        Assert.assertEquals(template.getAttributes().getMovement(),
                (Integer) 2);
        Assert.assertEquals(template.getAttributes().getSkill(), (Integer) 3);
        Assert.assertEquals(template.getAttributes().getSpeed(), (Integer) 4);
        Assert.assertEquals(template.getAttributes().getStrength(),
                (Integer) 5);

        Assert.assertEquals(template.getPosition(), TeamPosition.STRIKER);

        Assert.assertEquals(template.isGiant(), (Boolean) false);

        Assert.assertEquals(template.getAbilities().size(), 1);
    }

}
