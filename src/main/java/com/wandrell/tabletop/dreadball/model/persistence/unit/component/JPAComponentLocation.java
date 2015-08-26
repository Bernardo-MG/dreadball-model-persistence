package com.wandrell.tabletop.dreadball.model.persistence.unit.component;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wandrell.persistence.PersistenceEntity;
import com.wandrell.tabletop.dreadball.model.unit.component.ComponentLocation;

@Entity(name = "ComponentLocation")
@Table(name = "component_locations")
public final class JPAComponentLocation
        implements ComponentLocation, PersistenceEntity, Serializable {

    private static final long serialVersionUID = -7589272908184471999L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id               = -1;
    @Column(name = "name", unique = true)
    private String            name             = "";

    public JPAComponentLocation() {
        super();
    }

    @Override
    public final String getComponentLocationName() {
        return name;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    public final void setComponentLocationName(final String name) {
        checkNotNull(name, "Received a null pointer as name");

        this.name = name;
    }

    @Override
    public final void setId(final Integer identifier) {
        checkNotNull(identifier, "Received a null pointer as identifier");

        id = identifier;
    }

}
