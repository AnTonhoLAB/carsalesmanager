/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Manufacturer;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class ManufacturerDAO extends HibernateDAO<Manufacturer, Serializable> {
    
    public ManufacturerDAO(Manufacturer entity) {
        super(entity);
    }
    
}
