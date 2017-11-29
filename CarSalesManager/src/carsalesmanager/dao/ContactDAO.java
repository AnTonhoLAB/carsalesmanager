/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;

import carsalesmanager.model.Contact;
import java.io.Serializable;

/**
 *
 * @author george
 */
public class ContactDAO extends HibernateDAO<Contact, Serializable>{
    
    public ContactDAO(Contact entity) {
        super(entity);
    }
    
}
