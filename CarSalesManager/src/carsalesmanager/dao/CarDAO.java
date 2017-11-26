/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Car;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class CarDAO  extends HibernateDAO<Car, Serializable> {
    
    public CarDAO(Car entity) {
        super(entity);
    }
    
}
