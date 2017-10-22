/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Organization;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrganizationDao {

    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationId);

    public void updateOrganization(Organization updateOrg);

    public Organization getOrganizationbyId(int orgId);

    public List<Organization> getAllOrganizations();
    
    public List<Organization> getOrganizationsByPersonID (int personId);

}
