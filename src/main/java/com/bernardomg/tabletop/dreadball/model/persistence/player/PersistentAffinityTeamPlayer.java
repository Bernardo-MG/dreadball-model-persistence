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

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

/**
 * Player with affinity groups, and various costs which will depend on how many
 * of such affinities are shared.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Entity(name = "AffinityTeamPlayer")
@DiscriminatorValue("affinity")
@Table(name = "AFFINITY_PLAYERS")
@NamedEntityGraph(name = "fullAffinityTeamPlayer", attributeNodes = {
        @NamedAttributeNode(value = "abilities", subgraph = "abilities"),
        @NamedAttributeNode(value = "affinities", subgraph = "affinities"),
        @NamedAttributeNode(value = "hated", subgraph = "hated") },
        subgraphs = {
                @NamedSubgraph(name = "abilities",
                        attributeNodes = @NamedAttributeNode("name")),
                @NamedSubgraph(name = "affinities",
                        attributeNodes = @NamedAttributeNode("name")),
                @NamedSubgraph(name = "hated",
                        attributeNodes = @NamedAttributeNode("name")) })
public class PersistentAffinityTeamPlayer
        extends AbstractPersistentAffinityTeamPlayer implements Serializable {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = -6317901977987115397L;

    /**
     * Default constructor.
     */
    public PersistentAffinityTeamPlayer() {
        super();
    }

}
