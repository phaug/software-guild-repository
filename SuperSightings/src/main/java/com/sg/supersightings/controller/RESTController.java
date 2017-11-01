///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.supersightings.controller;
//
//import com.sg.supersightings.dao.LocationDao;
//import com.sg.supersightings.dao.OrganizationDao;
//import com.sg.supersightings.dao.PowerDao;
//import com.sg.supersightings.dao.SightingDao;
//import com.sg.supersightings.dao.SuperPersonDao;
//import com.sg.supersightings.model.Location;
//import com.sg.supersightings.model.Organization;
//import com.sg.supersightings.model.Power;
//import com.sg.supersightings.model.Sighting;
//import com.sg.supersightings.model.SuperPerson;
//import java.util.List;
//import javax.inject.Inject;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
///**
// *
// * @author apprentice
// */
//@CrossOrigin
//@Controller
//public class RESTController {
//
//    private LocationDao lDao;
//    private OrganizationDao oDao;
//    private PowerDao pDao;
//    private SightingDao sDao;
//    private SuperPersonDao spDao;
//
//    @Inject
//    public RESTController(LocationDao ldao) {
//        this.lDao = lDao;
//    }
//
//    @Inject
//    public RESTController(OrganizationDao oDao) {
//        this.oDao = oDao;
//    }
//
//    @Inject
//    public RESTController(PowerDao pDao) {
//        this.pDao = pDao;
//    }
//
//    @Inject
//    public RESTController(SightingDao sDao) {
//        this.sDao = sDao;
//    }
//
//    @Inject
//    public RESTController(SuperPersonDao spDao) {
//        this.spDao = spDao;
//    }
//
//    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Location getLocation(@PathVariable("id") int id) {
//        return lDao.getLocationbyId(id);
//    }
//
//    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Organization getOrganization(@PathVariable("id") int id) {
//        return oDao.getOrganizationbyId(id);
//    }
//
//    @RequestMapping(value = "/power/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Power getPower(@PathVariable("id") int id) {
//        return pDao.getPowerbyId(id);
//    }
//
//    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Sighting getSighting(@PathVariable("id") int id) {
//        return sDao.getSightingbyId(id);
//    }
//
//    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public SuperPerson getSuperPerson(@PathVariable("id") int id) {
//        return spDao.getPersonbyId(id);
//    }
//
//    @RequestMapping(value = "/location", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public Location createLocation(@RequestBody Location location) {
//        return lDao.addLocation(location);
//    }
//
////    @RequestMapping(value = "/organization", method = RequestMethod.POST)
////    @ResponseStatus(HttpStatus.CREATED)
////    @ResponseBody
////    public Organization createOrganization(@RequestBody Organization organization) {
////        return oDao.addOrganization(organization);
////    }
////
////    @RequestMapping(value = "/power", method = RequestMethod.POST)
////    @ResponseStatus(HttpStatus.CREATED)
////    @ResponseBody
////    public Power createPower(@RequestBody Power power) {
////        return pDao.addPower(power);
////    }
////
////    @RequestMapping(value = "/sighting", method = RequestMethod.POST)
////    @ResponseStatus(HttpStatus.CREATED)
////    @ResponseBody
////    public Sighting createSighting(@RequestBody Sighting sighting) {
////        return sDao.addSighting(sighting);
////    }
//
//    @RequestMapping(value = "/superPerson", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public SuperPerson createSuperPerson(@RequestBody SuperPerson superPerson) {
//        return spDao.addPerson(superPerson);
//    }
//
//    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteLocation(@PathVariable("id") int id) {
//        lDao.deleteLocation(id);
//    }
//
//    @RequestMapping(value = "/organization/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteOrganization(@PathVariable("id") int id) {
//        oDao.deleteOrganization(id);
//    }
//
//    @RequestMapping(value = "/power/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePowern(@PathVariable("id") int id) {
//        pDao.deletePower(id);
//    }
//
//    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteSighting(@PathVariable("id") int id) {
//        sDao.deleteSighting(id);
//    }
//
//    @RequestMapping(value = "/superPerson/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteSuperPerson(@PathVariable("id") int id) {
//        spDao.deletePerson(id);
//    }
//
//    @RequestMapping(value = "/location/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateLocation(@PathVariable("id") int id, @RequestBody Location location) {
//        // favor the path variable over the id in the object if they differ
//        location.setLocationId(id);
//        lDao.updateLocation(location);
//    }
//
//    @RequestMapping(value = "/organization/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateOrganization(@PathVariable("id") int id, @RequestBody Organization organization) {
//        // favor the path variable over the id in the object if they differ
//        organization.setOrganizationId(id);
//        oDao.updateOrganization(organization);
//    }
//
//    @RequestMapping(value = "/power/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updatePower(@PathVariable("id") int id, @RequestBody Power power) {
//        // favor the path variable over the id in the object if they differ
//        power.setPowerId(id);
//        pDao.updatePower(power);
//    }
//
//    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateSighting(@PathVariable("id") int id, @RequestBody Sighting sighting) {
//        // favor the path variable over the id in the object if they differ
//        sighting.setSightingId(id);
//        sDao.updateSighting(sighting);
//    }
//
//    @RequestMapping(value = "/superPerson/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateSuperPerson(@PathVariable("id") int id, @RequestBody SuperPerson superPerson) {
//        // favor the path variable over the id in the object if they differ
//        superPerson.setPersonId(id);
//        spDao.updatedPerson(superPerson);
//    }
//
//    @RequestMapping(value = "/powers", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Power> getAllPowers() {
//        return pDao.getAllPowers();
//    }
//
//    @RequestMapping(value = "/locations", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Location> getAllLocations() {
//        return lDao.getAllLocations();
//    }
//
//    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Organization> getAllOrganizations() {
//        return oDao.getAllOrganizations();
//    }
//
//    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Sighting> getAllSightings() {
//        return sDao.getAllSightings();
//    }
//
//    @RequestMapping(value = "/superPersons", method = RequestMethod.GET)
//    @ResponseBody
//    public List<SuperPerson> getAllSuperPersons() {
//        return spDao.getAllPersons();
//    }
//}
