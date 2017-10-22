/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface LocationDao {

    public void addLocation(Location location);

    public void deleteLocation(int locationId);

    public void updateLocation(Location updateLocation);

    public Location getLocationbyId(int id);

    public List<Location> getAllLocations();
    
    public List<Location> getLocationbyPersonId(int personId);
}
