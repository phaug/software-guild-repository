/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class SuperPerson {
    
    private int personId;
    private String superName;
    private String description;
    private int side;
    private Power power;
    private List<Organization> organization;
    private List<Sighting> sighting;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public List<Organization> getOrganization() {
        return organization;
    }

    public void setOrganization(List<Organization> organization) {
        this.organization = organization;
    }

    public List<Sighting> getSighting() {
        return sighting;
    }

    public void setSighting(List<Sighting> sighting) {
        this.sighting = sighting;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.personId;
        hash = 71 * hash + Objects.hashCode(this.superName);
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + this.side;
        hash = 71 * hash + Objects.hashCode(this.power);
        hash = 71 * hash + Objects.hashCode(this.organization);
        hash = 71 * hash + Objects.hashCode(this.sighting);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuperPerson other = (SuperPerson) obj;
        if (this.personId != other.personId) {
            return false;
        }
        if (this.side != other.side) {
            return false;
        }
        if (!Objects.equals(this.superName, other.superName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.power, other.power)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        if (!Objects.equals(this.sighting, other.sighting)) {
            return false;
        }
        return true;
    }

    public void getSighting(List<Sighting> findSightingsForPerson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
