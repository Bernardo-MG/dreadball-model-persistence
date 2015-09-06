package com.wandrell.tabletop.dreadball.model.persistence.availability.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.availability.unit.TeamTypeMVPAvailability;
import com.wandrell.tabletop.dreadball.model.faction.TeamType;
import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamType;
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAUnit;
import com.wandrell.tabletop.dreadball.model.unit.Unit;

@Entity(name = "TeamTypeMVPAvailability")
@Table(name = "team_type_mvp_avas")
public final class JPATeamTypeMVPAvailability
        implements TeamTypeMVPAvailability, PersistenceEntity, Serializable {

    private static final long serialVersionUID = -6257561802154856009L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_type_id")
    private JPATeamType       teamType;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private JPAUnit           unit;

    public JPATeamTypeMVPAvailability() {
        super();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final JPATeamTypeMVPAvailability other;

        other = (JPATeamTypeMVPAvailability) obj;
        return Objects.equals(teamType, other.teamType)
                && Objects.equals(unit, other.unit);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final TeamType getTeamType() {
        return teamType;
    }

    @Override
    public final Unit getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(unit, teamType);
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void setTeamType(final JPATeamType team) {
        checkNotNull(team, "Received a null pointer as team type");

        this.teamType = team;
    }

    public final void setUnit(final JPAUnit unit) {
        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("team", teamType)
                .add("unit", unit).toString();
    }

}
