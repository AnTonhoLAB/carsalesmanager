/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Car;
import carsalesmanager.model.CarType;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class TypeDao extends HibernateDao<CarType, Serializable> {
    
    public TypeDao(CarType entity) {
        super(entity);
    }
    
}
