/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.model.bo;

import carsalesmanager.dao.AccessoryDAO;
import carsalesmanager.dao.CarDAO;
import carsalesmanager.dao.ColorDAO;
import carsalesmanager.dao.ManufacturerDAO;
import carsalesmanager.dao.ModelDAO;
import carsalesmanager.dao.TypeDao;
import carsalesmanager.model.Accessory;
import carsalesmanager.model.Car;
import carsalesmanager.model.CarType;
import carsalesmanager.model.Color;
import carsalesmanager.model.Manufacturer;
import carsalesmanager.model.Model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    
        if(car.getModel().getManufacturer().getName().length() < 2)
            throw  new Exception("O campo \"marca\" precisa ser preenchido");
        if(car.getModel().getName().length() == 0)
            throw new Exception("O campo \"modelo\" precisa ser preenchido");
        if(car.getPlate().length()<8)
            throw new Exception("O campo placa precisa ser informado com 3 letras hífen e 4 numeros");
        if(car.getAge() < 1900 || car.getAge() > 2017)
            throw new Exception("O carro deve ter um ano entre 1900 e o ano atual");
        if(car.getColor().getName() == "")
            throw new Exception("Escolha uma cor para o carro");
        if(car.getKm() < 0)
            throw new Exception("Informe a kilometragem do veiculo");
        CarDAO cDao = new CarDAO(new Car());
        ArrayList<Car> allCars = (ArrayList<Car>) cDao.findAll();
        for (Car c : allCars) {
            if (c.getPlate().contains(car.getPlate())){
                throw new Exception("A placa ja existe");
            }
        }
    }
    
    public void save(Car car) throws Exception{
       validate(car);
        try {
             car.setModel(configureModel(car.getModel()));
             car.setCarType(configureType(car.getCarType()));
             car.setColor(configureColor(car.getColor()));
             car.setAccessories(configureAcces(car.getAccessories()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       //car.setCarType(carType);
       
        try {
            
            this.cDao.save(car);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
       System.out.println("foi");
       
    }
    
    private void delete(Car car) throws Exception{
        this.cDao.delete(car);
    }
    
    private Model configureModel(Model model) throws Exception{
        
        ManufacturerDAO manuDao = new ManufacturerDAO(new Manufacturer());
        ModelDAO mDao = new ModelDAO(new Model());
        
        ArrayList<Manufacturer> allManu = (ArrayList<Manufacturer>) manuDao.findAll();
        
        for (Manufacturer manufacturer : allManu) {
            if(manufacturer.getName().contains(model.getManufacturer().getName()))
            model.setManufacturer(manufacturer);
        }
        
        ArrayList<Model> allModel= (ArrayList<Model>) mDao.findAll();
        
        boolean saveNewModel = false;
        
        for (Model mod : allModel) {
            if (mod.getName().contains(model.getName())){
                System.out.println(mod.getName());
                model = mod;
                saveNewModel = false;
                break;
            }else{
                saveNewModel = true;
            }
        }
        System.out.println(saveNewModel);
        if (saveNewModel == true)
             mDao.save(model);
               
        return model;
    }
    
    private CarType configureType(CarType cType){
    
        TypeDao tDao = new TypeDao(new CarType());
        ArrayList<CarType> allTypes = (ArrayList<CarType>) tDao.findAll();
        
        for (CarType type : allTypes) {
            if(type.getName().contains(cType.getName())){
                cType = type;
            }
        }
        return cType;
    }

    private Color configureColor(Color color){
        ColorDAO cDao = new ColorDAO(new Color());
        ArrayList<Color> allColors = (ArrayList<Color>) cDao.findAll();
        
        for (Color c : allColors) {
            if(c.getName().contains(color.getName())){
                color = c;
            }
        }
        
        return color;
    }
    
    private Set<Accessory> configureAcces(Set<Accessory> accessories){
        AccessoryDAO aDao = new AccessoryDAO(new Accessory());
        Set<Accessory> allAcessory = new HashSet<>(aDao.findAll());
        Set<Accessory> toReturn = new HashSet<>();
        
        for (Accessory a : allAcessory) {
            for (Accessory ac : accessories) {
                if (a.getName() == ac.getName()){
                    toReturn.add(a);
                }
            }
        }
        
        return toReturn;
    }   
}
