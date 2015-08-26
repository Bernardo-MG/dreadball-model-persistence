package com.wandrell.tabletop.dreadball.model.persistence.unit.component;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "UnitComponent")
@Table(name = "unit_components")
public final class JPAUnitComponent extends AbstractJPAUnitComponent
        implements Serializable {

    private static final long serialVersionUID = -5547831116001472121L;
    @Column(name = "cost")
    private Integer           cost;

    public JPAUnitComponent() {
        super();
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    public final void setCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        this.cost = cost;
    }

}
