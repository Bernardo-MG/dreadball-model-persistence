package com.wandrell.tabletop.testing.dreadball.model.persistence.util.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wandrell.tabletop.dreadball.model.persistence.faction.JPASponsor;

public interface JPASponsorTestRepository
        extends SponsorTestRepository, Repository<JPASponsor, Integer> {

    @Override
    @Query("SELECT sponsor FROM Sponsor sponsor WHERE sponsor.id = :id")
    public JPASponsor findById(@Param("id") int id);

}
