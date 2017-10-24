/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Organization {

    private int organizationId;
    private String orgName;
    private String description;
    private String phone;
    private List<SuperPerson> superPersons  = new ArrayList<>();
    private Location location;

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SuperPerson> getSuperPersons() {
        return superPersons;
    }

    public void setSuperPersons(List<SuperPerson> superPersons) {
        this.superPersons = superPersons;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.organizationId;
        hash = 71 * hash + Objects.hashCode(this.orgName);
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + Objects.hashCode(this.phone);
        hash = 71 * hash + Objects.hashCode(this.superPersons);
        hash = 71 * hash + Objects.hashCode(this.location);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.superPersons, other.superPersons)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }



    
}
