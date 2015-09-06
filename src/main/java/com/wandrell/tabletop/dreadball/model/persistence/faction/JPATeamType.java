package com.wandrell.tabletop.dreadball.model.persistence.faction;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.faction.TeamRule;
import com.wandrell.tabletop.dreadball.model.faction.TeamType;

@Entity(name = "TeamType")
@Table(name = "team_types")
public final class JPATeamType
        implements TeamType, PersistenceEntity, Serializable {

    private static final long             serialVersionUID = -6484889622281976716L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                       id               = -1;
    @Column(name = "name", unique = true)
    private String                        name             = "";
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "team_type_rules",
            joinColumns = { @JoinColumn(name = "team_type_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "team_rule_id",
                    referencedColumnName = "id") })
    private final Collection<JPATeamRule> rules            = new LinkedHashSet<JPATeamRule>();

    public JPATeamType() {
        super();
    }

    public final void addTeamRule(final TeamRule rule) {
        checkNotNull(rule, "Received a null pointer as rule");
        checkArgument(rule instanceof JPATeamRule,
                "The TeamRule should be an instanceof JPATeamRule");

        getTeamRulesModifiable().add((JPATeamRule) rule);
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

        final JPATeamType other;

        other = (JPATeamType) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Collection<TeamRule> getTeamRules() {
        final Collection<TeamRule> col;

        col = new LinkedList<>();
        for (final TeamRule rule : getTeamRulesModifiable()) {
            col.add(rule);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public final String getTeamTypeName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    public final void removeTeamRule(final TeamRule rule) {
        getTeamRulesModifiable().remove(rule);
    }

    @Override
    public final void setId(final Integer id) {
        checkNotNull(id, "Received a null pointer as id");

        this.id = id;
    }

    public final void setTeamRules(final Collection<TeamRule> rules) {
        checkNotNull(rules, "Received a null pointer as rules");

        getTeamRulesModifiable().clear();

        for (final TeamRule rule : rules) {
            checkArgument(rule instanceof JPATeamRule,
                    "All the rules should be an instanceof JPATeamRule");

            getTeamRulesModifiable().add((JPATeamRule) rule);
        }
    }

    public final void setTeamTypeName(final String name) {
        checkNotNull(name, "Received a null pointer as name");

        this.name = name;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    private final Collection<JPATeamRule> getTeamRulesModifiable() {
        return rules;
    }

}
