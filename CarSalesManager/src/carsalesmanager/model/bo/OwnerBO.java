/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.model.bo;

import carsalesmanager.dao.AddressDAO;
import carsalesmanager.dao.CityDAO;
import carsalesmanager.dao.ContactDAO;
import carsalesmanager.dao.OwnerDAO;
import carsalesmanager.dao.StateDAO;
import carsalesmanager.model.Address;
import carsalesmanager.model.City;
import carsalesmanager.model.Contact;
import carsalesmanager.model.Owner;
import carsalesmanager.model.State;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author george
 */
public class OwnerBO {
    
    private OwnerDAO oDao;

    public OwnerBO() {
        this.oDao = new OwnerDAO(new Owner());
    }
    
    private void validate(Owner owner)throws Exception{
     
        if(owner.getName().length() == 0)
             throw  new Exception("O campo \"nome\" precisa ser preenchido");
        Set<Contact> contacts = owner.getContacts();
        for (Contact contact : contacts) {
            if (contact.getTelephone().length() < 9)
                 throw  new Exception("Preencha o telefone corretamente sem o ddd \n apenas com um hífen entre os numeros ex 3490-5488");
            if (contact.getEmail().length() < 4)
                throw new Exception("Informe um email com mais de 4 digitos");
        }
        if(owner.getCpf().length() < 14)
            throw new Exception("Informe o cpf seguido a regra de: \n 3 numeros ponto 3 numeros ponto 3 numeros traço 2 numeros");
        Set <Owner> lis = new HashSet<Owner> (this.oDao.findAll());
        for (Owner li : lis) {
            if(li.getCpf().contains(owner.getCpf())){
                throw new Exception ("O Cpf informado ja esta cadastrado");
            }
        }
        if(owner.getAddress().getStreet().length() == 0)
            throw new Exception ("Não deixe o campo rua em branco");
        if(owner.getAddress().getCity().getName().length() == 0)
            throw new Exception("Não deixe o campo cidade em branco");
        if(owner.getAddress().getHouseNumber() == 0)
            throw new Exception("Informe um numero que não seja 0");
        if (owner.getAddress().getCity().getState().getName().length() == 0)
            throw new Exception("Informe o estado em que o comprador mora");
    }
    
    public void save(Owner owner) throws Exception{
        validate(owner);     
        owner.setAddress(getAddress(owner.getAddress()));
        owner.setContacts(getContact(owner.getContacts()));
        this.oDao.save(owner);
    }
    
    
   private Address getAddress(Address ad) throws Exception{
       StateDAO sDao = new StateDAO(new State());
       CityDAO cDao = new CityDAO(new City());
       AddressDAO aDao = new AddressDAO(new Address());
       
       ArrayList<State> states = (ArrayList<State>) sDao.findAll();
       for (State state : states) {
           if (state.getName().contains(ad.getCity().getState().getName()))
               ad.getCity().setState(state);
        }
       
       cDao.save(ad.getCity());
       aDao.save(ad);
       
       return ad;
   }
   
   private Set<Contact> getContact(Set<Contact> cont) throws Exception{
       ContactDAO cDao = new ContactDAO(new Contact());
       
       for (Contact contact : cont) {
             cDao.save(contact);
       }
       return cont;
   }
    
}
