/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Address;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class AddressDAO extends HibernateDAO<Address, Serializable>{
    
    public AddressDAO(Address entity) {
        super(entity);
    }
    
}
