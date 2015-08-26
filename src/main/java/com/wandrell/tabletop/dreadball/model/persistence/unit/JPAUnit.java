package com.wandrell.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.unit.Unit;

@Entity(name = "Unit")
@Table(name = "units")
public final class JPAUnit extends AbstractJPAUnit
        implements Unit, PersistenceEntity, Serializable {

    private static final long serialVersionUID = -6317901977987115397L;
    @Column(name = "cost")
    private Integer           cost             = 0;

    public JPAUnit() {
        super();
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    public final void setCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as ally cost");

        this.cost = cost;
    }

}
