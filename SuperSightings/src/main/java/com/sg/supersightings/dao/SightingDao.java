/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SightingDao {

    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingId);

    public void updateSighting(Sighting updateSighting);

    public Sighting getSightingbyId(int id);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> getSightingsByPersonId (int personId);
    
    public List<Sighting> getSightingbyLocationIdandDate( int locationId, LocalDate date);
    
    public List<Sighting> getAllSightingsbyDate (LocalDate date);

    public List<Sighting> findLastTenSightings();
    
    public List<Sighting> getSightingbyLocationId(int locationId);

}
