/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Accessory;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class AccessoryDAO extends HibernateDAO<Accessory, Serializable>{
    
    public AccessoryDAO(Accessory entity) {
        super(entity);
    }
    
}
