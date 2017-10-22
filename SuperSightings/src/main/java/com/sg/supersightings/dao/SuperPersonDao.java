/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.SuperPerson;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SuperPersonDao {

    public void addPerson(SuperPerson person);

    public void deletePerson(int superPersonId);

    public void updatedPerson(SuperPerson updatePerson);

    public SuperPerson getPersonbyId(int id);

    public List<SuperPerson> getAllPersons();

    public List<SuperPerson> getPersonByLocationId(int locationId);
    
    public List<SuperPerson> getPersonsByOrganizationId (int orgId);
    
    

}
