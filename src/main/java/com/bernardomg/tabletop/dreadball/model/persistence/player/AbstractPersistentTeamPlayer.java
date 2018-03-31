/**
 * Copyright 2015 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.bernardomg.tabletop.dreadball.model.persistence.player;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.persistence.player.stats.PersistentAbility;
import com.bernardomg.tabletop.dreadball.model.persistence.player.stats.PersistentAttributes;
import com.bernardomg.tabletop.dreadball.model.player.Role;
import com.bernardomg.tabletop.dreadball.model.player.TeamPlayer;
import com.bernardomg.tabletop.dreadball.model.player.stats.Ability;
import com.bernardomg.tabletop.dreadball.model.player.stats.Attributes;
import com.google.common.base.MoreObjects;

/**
 * Abstract root for the basic features all the Dreadball players have, no
 * matter if they come from Dreadball Original (DBO) or Dreadball Xtreme (DBX).
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "TeamPlayer")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "player_type")
@Table(name = "players")
public abstract class AbstractPersistentTeamPlayer implements TeamPlayer {

    /**
     * Player abilities.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "player_abilities",
            joinColumns = { @JoinColumn(name = "player_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "ability_id",
                    referencedColumnName = "id") })
    private final Collection<PersistentAbility> abilities    = new LinkedHashSet<>();

    /**
     * Player attributes.
     */
    @Embedded
    private PersistentAttributes                attributes   = new PersistentAttributes();

    /**
     * Player cost.
     */
    @Column(name = "cost")
    private Integer                             cost         = 0;

    /**
     * Flag indicating if the player is a giant.
     */
    @Column(name = "giant")
    private Boolean                             giant        = false;

    /**
     * Player's primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer                             id           = -1;

    /**
     * Flag indicating if the player is a MVP.
     */
    @Column(name = "mvp")
    private final Boolean                       mvp          = false;

    /**
     * Player name.
     */
    @Column(name = "name")
    private String                              name         = "";

    /**
     * Player team role.
     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role                                role         = Role.JACK;

    /**
     * Player template name.
     */
    @Column(name = "template_name", unique = true)
    private String                              templateName = "";

    /**
     * Default constructor.
     */
    public AbstractPersistentTeamPlayer() {
        super();
    }

    /**
     * Adds an ability to the player.
     * 
     * @param ability
     *            the ability to add
     */
    public final void addAbility(final Ability ability) {
        checkArgument(ability instanceof PersistentAbility,
                "The Ability should be an instanceof JPAAbility");

        getAbilitiesModifiable().add((PersistentAbility) ability);
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

        final AbstractPersistentTeamPlayer other;

        other = (AbstractPersistentTeamPlayer) obj;
        return Objects.equals(templateName, other.templateName)
                && Objects.equals(name, other.name);
    }

    @Override
    public final Collection<Ability> getAbilities() {
        final Collection<Ability> col;

        col = new ArrayList<>();
        for (final Ability ability : getAbilitiesModifiable()) {
            col.add(ability);
        }

        return Collections.unmodifiableCollection(col);
    }

    @Override
    public final Attributes getAttributes() {
        return attributes;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Boolean getGiant() {
        return giant;
    }

    /**
     * Returns the ID assigned to this player.
     * 
     * @return the entity's ID
     */
    public final Integer getId() {
        return id;
    }

    @Override
    public final Boolean getMvp() {
        return mvp;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Role getRole() {
        return role;
    }

    @Override
    public final String getTemplateName() {
        return templateName;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(templateName, name);
    }

    /**
     * Removes an ability from the player.
     * 
     * @param ability
     *            the ability to remove
     */
    public final void removeAbility(final Ability ability) {
        getAbilitiesModifiable().remove(ability);
    }

    /**
     * Sets the player abilities.
     * <p>
     * All the abilities which the player currently has will be removed and
     * swapped with the received ones.
     * 
     * @param playerAbilities
     *            the abilities to set on the player
     */
    public final void setAbilities(final Collection<Ability> playerAbilities) {
        getAbilitiesModifiable().clear();

        if (playerAbilities != null) {
            for (final Ability ability : playerAbilities) {
                checkArgument(ability instanceof PersistentAbility,
                        "All the abilities should be an instanceof JPAAbility");

                getAbilitiesModifiable().add((PersistentAbility) ability);
            }
        }
    }

    /**
     * Sets the player attributes.
     * 
     * @param attrs
     *            the attributes for the player
     */
    public final void setAttributes(final Attributes attrs) {
        checkArgument(attrs instanceof PersistentAttributes,
                "The Attributes should be an instanceof JPAAttributes");

        attributes = (PersistentAttributes) attrs;
    }

    /**
     * Sets the player's cost.
     * 
     * @param costPlayer
     *            the player's cost
     */
    public final void setCost(final Integer costPlayer) {
        cost = costPlayer;
    }

    /**
     * Sets the player giant flag.
     * 
     * @param giantFlag
     *            the flag indicating if the player is a giant
     */
    public final void setGiant(final Boolean giantFlag) {
        giant = giantFlag;
    }

    /**
     * Sets the ID assigned to this entity.
     * 
     * @param identifier
     *            the ID for the entity
     */
    public final void setId(final Integer identifier) {
        id = identifier;
    }

    /**
     * Sets the player name.
     * 
     * @param playerName
     *            the player name
     */
    public final void setName(final String playerName) {
        name = playerName;
    }

    /**
     * Sets the player team role.
     * 
     * @param playerRole
     *            the team role for the player
     */
    public final void setRole(final Role playerRole) {
        role = playerRole;
    }

    /**
     * Sets the player's template name.
     * 
     * @param tname
     *            the template name
     */
    public final void setTemplateName(final String tname) {
        templateName = tname;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", templateName)
                .add("id", id).toString();
    }

    /**
     * Returns the modifiable collection of the player's abilities.
     * 
     * @return the modifiable collection of the player's abilities
     */
    private final Collection<PersistentAbility> getAbilitiesModifiable() {
        return abilities;
    }

}
