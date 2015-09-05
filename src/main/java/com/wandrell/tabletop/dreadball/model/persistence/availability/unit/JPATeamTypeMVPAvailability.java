package com.wandrell.tabletop.dreadball.model.persistence.availability.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    private JPATeamType       team;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private JPAUnit           unit;

    public JPATeamTypeMVPAvailability() {
        super();
    }

    @Override
    public final Integer getId() {
        return id;
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

    public final void setTeamType(final JPATeamType team) {
        this.team = team;
    }

    public final void setUnit(final JPAUnit unit) {
        this.unit = unit;
    }

}
