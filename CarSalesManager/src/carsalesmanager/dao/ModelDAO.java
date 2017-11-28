/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Model;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class ModelDAO extends HibernateDAO<Model, Serializable>{
    
    public ModelDAO(Model entity) {
        super(entity);
    }
    
}
