/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Sighting {

    private int sightingId;
    private LocalDate date;
    private Location location;
    private List<SuperPerson> superPerson;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<SuperPerson> getSuperPerson() {
        return superPerson;
    }

    public void setSuperPerson(List<SuperPerson> superPerson) {
        this.superPerson = superPerson;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.sightingId;
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.location);
        hash = 97 * hash + Objects.hashCode(this.superPerson);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.superPerson, other.superPerson)) {
            return false;
        }
        return true;
        
    }

    @Override
    public String toString() {
        return "Sighting{" + "sightingId=" + sightingId + ", date=" + date + ", location=" + location + ", superPerson=" + superPerson + '}';
    }
    


}
