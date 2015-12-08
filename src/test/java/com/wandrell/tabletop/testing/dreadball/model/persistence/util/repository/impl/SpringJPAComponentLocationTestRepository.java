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

package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.unit.component.JPAComponentLocation;
import com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository.api.ComponentLocationTestRepository;

/**
 * Spring Data JPA implementation of {@link ComponentLocationTestRepository}
 * 
 * @author Bernardo Martínez Garrido
 */
public interface SpringJPAComponentLocationTestRepository
        extends ComponentLocationTestRepository,
        Repository<JPAComponentLocation, Integer> {

    @Override
    @Query("SELECT location FROM ComponentLocation location WHERE location.id = :id")
    public JPAComponentLocation findById(@Param("id") int id);

}
