package com.wandrell.tabletop.dreadball.model.persistence.availability.team;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.availability.team.SponsorAssetsAvailability;

@Entity(name = "SponsorAssetsAvailability")
@Table(name = "sponsor_asset_avas")
public final class JPASponsorAssetsAvailability
        implements SponsorAssetsAvailability, PersistenceEntity, Serializable {

    private static final long serialVersionUID      = -7367447173870314061L;
    @Column(name = "cost_affinity")
    private Integer           costAffinity          = 0;
    @Column(name = "cost_cheerleader")
    private Integer           costCheerleader       = 0;
    @Column(name = "cost_cheerleader_unlock")
    private Integer           costCheerleaderUnlock = 0;
    @Column(name = "cost_dice")
    private Integer           costDice              = 0;
    @Column(name = "cost_medibot")
    private Integer           costMedibot           = 0;
    @Column(name = "cost_sabotage")
    private Integer           costSabotage          = 0;
    @Column(name = "cost_special_move")
    private Integer           costSpecialMove       = 0;
    @Column(name = "cost_wager")
    private Integer           costWager             = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id                    = -1;
    @Column(name = "max_wager")
    private Integer           maxWager              = 0;
    @Column(name = "min_team_cost")
    private Integer           minTeamCost           = 0;

    public JPASponsorAssetsAvailability() {
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

        final JPASponsorAssetsAvailability other;

        other = (JPASponsorAssetsAvailability) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public final Integer getAffinityGroupCost() {
        return costAffinity;
    }

    @Override
    public final Integer getCheerleaderCost() {
        return costCheerleader;
    }

    @Override
    public final Integer getCheerleaderUnlockCost() {
        return costCheerleaderUnlock;
    }

    @Override
    public final Integer getCoachingDieCost() {
        return costDice;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Integer getMediBotCost() {
        return costMedibot;
    }

    @Override
    public final Integer getSabotageCardCost() {
        return costSabotage;
    }

    @Override
    public final Integer getSpecialMovementCardCost() {
        return costSpecialMove;
    }

    @Override
    public final Integer getTeamCostMin() {
        return minTeamCost;
    }

    @Override
    public final Integer getWagerCost() {
        return costWager;
    }

    @Override
    public final Integer getWagerMaxCount() {
        return maxWager;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    public final void setAffinityGroupCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costAffinity = cost;
    }

    public final void setCheerleaderCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costCheerleader = cost;
    }

    public final void setCheerleaderUnlockCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costCheerleaderUnlock = cost;
    }

    public final void setCoachingDieCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costDice = cost;
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void setMediBotCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costMedibot = cost;
    }

    public final void setSabotageCardCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costSabotage = cost;
    }

    public final void setSpecialMovementCardCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costSpecialMove = cost;
    }

    public final void setTeamCostMin(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        minTeamCost = cost;
    }

    public final void setWagerCost(final Integer cost) {
        checkNotNull(cost, "Received a null pointer as cost");

        costWager = cost;
    }

    public final void setWagerMaxCount(final Integer max) {
        checkNotNull(max, "Received a null pointer as max");

        maxWager = max;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).toString();
    }

}
