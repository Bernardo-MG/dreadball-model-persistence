package com.wandrell.tabletop.dreadball.model.persistence.unit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAUnitComponent;
import com.wandrell.tabletop.dreadball.model.unit.AdvancementUnit;
import com.wandrell.tabletop.dreadball.model.unit.component.UnitComponent;

@Entity(name = "AdvancementUnit")
@Table(name = "advancement_units")
public final class JPAAdvancementUnit extends AbstractJPAUnit
        implements AdvancementUnit, PersistenceEntity, Serializable {

    private static final long serialVersionUID = -6317901977987115397L;
    @Column(name = "cost")
    private Integer           cost             = 0;
    @Column(name = "experience")
    private Integer           experience       = 0;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grafted_implant_id")
    private JPAUnitComponent  implant;
    @Column(name = "rank")
    private Integer           rank             = 0;

    public JPAAdvancementUnit() {
        super();
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final UnitComponent getGraftedImplant() {
        return implant;
    }

    @Override
    public final Integer getRank() {
        return rank;
    }

    @Override
    public final Integer getUnspentExperience() {
        return experience;
    }

    @Override
    public final Integer getValoration() {
        // TODO Auto-generated method stub
        return null;
    }

    public final void setCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        this.cost = cost;
    }

    @Override
    public final void setGraftedImplant(final UnitComponent implant) {
        checkNotNull(implant, "Received a null pointer as implant");
        checkArgument(implant instanceof JPAUnitComponent,
                "The implant should be an instance of JPAUnitComponent");

        this.implant = (JPAUnitComponent) implant;
    }

    @Override
    public final void setRank(final Integer rank) {
        checkNotNull(rank, "Received a null pointer as rank");

        this.rank = rank;
    }

    @Override
    public final void setUnspentExperience(final Integer experience) {
        checkNotNull(experience, "Received a null pointer as experience");

        this.experience = experience;
    }

}
