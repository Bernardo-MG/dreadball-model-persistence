package com.wandrell.tabletop.testing.dreadball.model.persistence.test.integration.unit.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAComponentLocation;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.ComponentLocationTestRepository;

@ContextConfiguration(locations = { "/spring/persistence.xml" })
public final class ITJPAComponentLocation
        extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ComponentLocationTestRepository repository;

    public ITJPAComponentLocation() {
        super();
    }

    @Test
    public void test_loads() {
        final JPAComponentLocation location;

        location = repository.findById(3);

        Assert.assertEquals(location.getId(), (Integer) 3);
        Assert.assertEquals(location.getComponentLocationName(), "location3");
    }

}
