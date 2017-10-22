/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Organization;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class OrganizationDaoJdbcTemplateImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (orgName, orgDescription, phoneNum, locationId) "
            + "value (?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organizationId = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set orgName = ?, orgDescription = ?, phoneNum = ?, "
            + "locationId = ? where organizationId = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where organizationId = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";

    private static final String SQL_SELECT_ORGANIZATION_BY_PERSONID
            = "select o.* from organization o "
            + "inner join superpersonorganization on o.organizationId = superpersonorganization.organizationId "
            + "inner join superperson p on p.superPersonId = superpersonorganization.superPersonId "
            + "where p.superPersonId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrgName(),
                organization.getOrgDescription(),
                organization.getLocation(),
                organization.getPhoneNum());
        
    }

    @Override
    public void deleteOrganization(int organizationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOrganization(Organization updateOrg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization getOrganizationbyId(int orgId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getOrganizationsByPersonID(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
