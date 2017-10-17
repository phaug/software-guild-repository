/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.State;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public interface StateDao {

    public Map<String, Double> taxPercentage() throws StatePersistenceException;

    void loadFile() throws StatePersistenceException;
    
    public Double getState(String stateName)
            throws StatePersistenceException;
}

