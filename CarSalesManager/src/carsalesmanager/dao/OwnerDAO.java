/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Owner;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class OwnerDAO extends HibernateDAO<Owner, Serializable>{
    
    public OwnerDAO(Owner entity) {
        super(entity);
    }
    
}
