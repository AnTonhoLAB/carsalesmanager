/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Sale;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class SaleDAO extends HibernateDAO<Sale, Serializable>{
    
    public SaleDAO(Sale entity) {
        super(entity);
    }
    
}
