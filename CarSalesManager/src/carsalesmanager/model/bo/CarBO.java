/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.model.bo;

import carsalesmanager.dao.CarDAO;
import carsalesmanager.model.Car;

/**
 *
 * @author george
 */
public class CarBO {
    private CarDAO cDao;
    
    public CarBO(){
         this.cDao = new CarDAO(new Car());
    }
    
    private void validate(Car car) throws Exception{
        if(car.getAge() < 1900 || car.getAge() > 2017){
            throw new Exception("O carro deve ter um ano entre 1900 e o ano atual");
        }      
        
    }
    
    public void save(Car car) throws Exception{
        validate(car);
        System.out.println("foi");
       this.cDao.save(car);
    }
    
    private void delete(Car car) throws Exception{
        this.cDao.delete(car);
    }
    
}
