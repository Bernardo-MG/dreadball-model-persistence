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
import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.bernardomg.tabletop.dreadball.model.persistence.player.component.PersistentComponent;
import com.bernardomg.tabletop.dreadball.model.player.AdvancementTeamPlayer;
import com.bernardomg.tabletop.dreadball.model.player.component.Component;

/**
 * Abstract root for a player which may change and evolve over time, usually
 * between matches.
 * <p>
 * This is a persistent JPA-Based implementation.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@MappedSuperclass
public abstract class AbstractPersistentAdvancementTeamPlayer
        extends AbstractPersistentTeamPlayer implements AdvancementTeamPlayer {

    /**
     * Player grafted implant.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grafted_implant_id")
    private PersistentComponent graftedImplant;

    /**
     * Player name.
     */
    @Column(name = "name")
    private final String        name       = "";

    /**
     * Player rank.
     */
    @Column(name = "rank")
    private Integer             rank       = 0;

    /**
     * Player experience.
     */
    @Column(name = "experience")
    private Integer             unspentExperience = 0;

    /**
     * Default constructor.
     */
    public AbstractPersistentAdvancementTeamPlayer() {
        super();
    }

    @Override
    public final Component getGraftedImplant() {
        return graftedImplant;
    }

    @Override
    public final Integer getRank() {
        return rank;
    }

    @Override
    public final Integer getUnspentExperience() {
        return unspentExperience;
    }

    @Override
    public final Integer getValoration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final void setGraftedImplant(final Component graft) {
        checkNotNull(graft, "Received a null pointer as implant");
        checkArgument(graft instanceof PersistentComponent,
                "The implant should be an instance of JPAComponent");

        graftedImplant = (PersistentComponent) graft;
    }

    @Override
    public final void setRank(final Integer rankPlayer) {
        checkNotNull(rankPlayer, "Received a null pointer as rank");

        rank = rankPlayer;
    }

    @Override
    public final void setUnspentExperience(final Integer exp) {
        checkNotNull(exp, "Received a null pointer as experience");

        unspentExperience = exp;
    }

}
