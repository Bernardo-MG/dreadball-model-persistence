package com.wandrell.tabletop.testing.dreadball.model.persistence.test.unit.faction;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPASponsor;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;

public final class TestJPASponsor {

    public TestJPASponsor() {
        super();
    }

    @Test
    public void test_groups_NoRepeat() {
        final JPASponsor sponsor;
        final JPAAffinityGroup group1;
        final JPAAffinityGroup group2;

        sponsor = new JPASponsor();

        group1 = new JPAAffinityGroup();
        group1.setId(1);
        group1.setAffinityGroupName("group1");

        group2 = new JPAAffinityGroup();
        group2.setId(2);
        group2.setAffinityGroupName("group2");

        sponsor.addPlayerGroup(group1);
        sponsor.addPlayerGroup(group2);
        sponsor.addPlayerGroup(group1);

        Assert.assertEquals(sponsor.getAffinityGroups().size(), 2);
    }

}
