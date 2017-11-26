/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.State;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class StateDAO extends HibernateDAO<State, Serializable> {
    
    public StateDAO(State entity) {
        super(entity);
    }
    
}
