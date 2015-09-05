package com.wandrell.tabletop.dreadball.model.persistence.availability.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.availability.unit.TeamTypeUnitAvailability;
import com.wandrell.tabletop.dreadball.model.faction.TeamType;
import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamType;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAUnit;
import com.wandrell.tabletop.dreadball.model.unit.Unit;

@Entity(name = "TeamTypeUnitAvailability")
@Table(name = "team_type_unit_avas")
public final class JPATeamTypeUnitAvailability
        implements TeamTypeUnitAvailability, PersistenceEntity, Serializable {

    private static final long serialVersionUID = -3725739240261330858L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;
    @Column(name = "initial")
    private Integer           initial          = 0;
    @Column(name = "max")
    private Integer           max              = 0;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_type_id")
    private JPATeamType       team;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private JPAUnit           unit;

    public JPATeamTypeUnitAvailability() {
        super();
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Integer getInitialNumber() {
        return initial;
    }

    @Override
    public final Integer getMaxNumber() {
        return max;
    }

    @Override
    public final TeamType getTeamType() {
        return team;
    }

    @Override
    public final Unit getUnit() {
        return unit;
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void setInitialNumber(final Integer initial) {
        checkNotNull(id, "Received a null pointer as id");

        this.initial = initial;
    }

    public final void setMaxNumber(final Integer max) {
        this.max = max;
    }

    public final void setTeamType(final JPATeamType team) {
        this.team = team;
    }

    public final void setUnit(final JPAUnit unit) {
        this.unit = unit;
    }

}
