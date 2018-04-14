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

package com.bernardomg.tabletop.dreadball.model.persistence.player.component;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bernardomg.tabletop.dreadball.model.persistence.player.AbstractPersistentAffinityTeamPlayer;
import com.bernardomg.tabletop.dreadball.model.player.component.Component;
import com.bernardomg.tabletop.dreadball.model.player.component.CompositeAffinityTeamPlayer;

/**
 * Composite affinity player.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "CompositeAffinityPlayer")
@DiscriminatorValue("composite_affinity")
@Table(name = "COMPOSITE_AFFINITY_PLAYERS")
public class PersistentCompositeAffinityTeamPlayer
        extends AbstractPersistentAffinityTeamPlayer
        implements CompositeAffinityTeamPlayer, Serializable {

    /**
     * Serialization ID.
     */
    private static final long                     serialVersionUID = -5866596776570200158L;

    /**
     * Player components.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PLAYER_COMPONENTS",
            joinColumns = { @JoinColumn(name = "player_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "component_id",
                    referencedColumnName = "id") })
    private final Collection<PersistentComponent> components       = new HashSet<PersistentComponent>();

    /**
     * Default constructor.
     */
    public PersistentCompositeAffinityTeamPlayer() {
        super();
    }

    /**
     * Adds a component.
     * 
     * @param component
     *            the component to add
     */
    public final void addComponent(final PersistentComponent component) {
        getComponentsModifiable().add(component);
    }

    @Override
    public Collection<Component> getComponents() {
        final Collection<Component> col;

        col = new ArrayList<>();
        for (final Component ability : getComponentsModifiable()) {
            col.add(ability);
        }

        return Collections.unmodifiableCollection(col);
    }

    /**
     * Removes a component.
     * 
     * @param component
     *            the component to remove
     */
    public final void removeComponent(final PersistentComponent component) {
        getComponentsModifiable().remove(component);
    }

    /**
     * Sets the player components.
     * <p>
     * If the player has any component these are removed and swapped with the
     * received ones.
     * 
     * @param playerComponents
     *            the components to set on the player
     */
    public void setComponents(final Collection<Component> playerComponents) {
        getComponentsModifiable().clear();

        if (playerComponents != null) {
            for (final Component component : playerComponents) {
                checkArgument(component instanceof PersistentComponent,
                        "All the components should be an instanceof JPAComponent");

                getComponentsModifiable().add((PersistentComponent) component);
            }
        }
    }

    /**
     * Returns the modifiable collection of the player's components.
     * 
     * @return the modifiable collection of the player's components
     */
    private final Collection<PersistentComponent> getComponentsModifiable() {
        return components;
    }

}
