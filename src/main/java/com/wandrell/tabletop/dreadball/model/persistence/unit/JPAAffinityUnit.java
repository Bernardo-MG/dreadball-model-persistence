package com.wandrell.tabletop.dreadball.model.persistence.unit;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "AffinityUnit")
@Table(name = "affinity_units")
public final class JPAAffinityUnit extends AbstractJPAAffinityUnit
        implements Serializable {

    private static final long serialVersionUID = -6317901977987115397L;

    public JPAAffinityUnit() {
        super();
    }

}
