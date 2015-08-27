package com.wandrell.tabletop.dreadball.model.persistence.unit;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "AdvancementUnit")
@Table(name = "advancement_units")
public final class JPAAdvancementUnit extends AbstractJPAAdvancementUnit
        implements Serializable {

    private static final long serialVersionUID = -6317901977987115397L;

    public JPAAdvancementUnit() {
        super();
    }

}
