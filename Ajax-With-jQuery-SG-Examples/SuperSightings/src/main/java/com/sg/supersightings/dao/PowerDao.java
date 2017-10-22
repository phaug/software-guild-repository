/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface PowerDao {

    public void addPower(Power power);

    public void deletePower(int powerId);

    public void updatePower(Power updatePower);

    public Power getPowerbyId(int id);

    public List<Power> getAllPowers();
}
