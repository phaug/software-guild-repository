/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Power {

    private int powerId;
    @NotEmpty(message = "You must supply a value for Power.")
    @Length(max = 50, message = "Power must be no more than 50 characters in length.")
    private String powerName;

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.powerId;
        hash = 83 * hash + Objects.hashCode(this.powerName);
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
        final Power other = (Power) obj;
        if (this.powerId != other.powerId) {
            return false;
        }
        if (!Objects.equals(this.powerName, other.powerName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Power{" + "powerId=" + powerId + ", powerName=" + powerName + '}';
    }

}
