/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.model.bo;

import carsalesmanager.dao.CarDAO;
import carsalesmanager.dao.TypeDao;
import carsalesmanager.model.Car;
import carsalesmanager.model.CarType;
import carsalesmanager.model.Manufacturer;
import java.util.ArrayList;

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
        
    }
    
    public void save(Car car) throws Exception{
        validate(car);
        
        
        System.out.println("foi");
       this.cDao.save(car);
    }
    
    private void delete(Car car) throws Exception{
        this.cDao.delete(car);
    }
    
    private void carType{
    
//     TypeDao tDao = new TypeDao(new CarType());
//        ArrayList<CarType> types = (ArrayList<CarType>) tDao.findAll();
//        
//        for (CarType type : types) {
//            if(type.getName().contains(chk.getText())){
//                cType = type;
//                System.out.println(cType.getName());
//            }
//        }
    }
    private void createCarModel(){
        
        //        
//        
//     Manufacturer manu = new Manufacturer();
//     Model mod = new Model();
//        try {
//            
//            manu.setName(CBManufacturer.getSelectionModel().getSelectedItem().toString());
//        } catch (Exception e) {
//            manu.setName("");
//        }
//     
//        try {
//            mod.setName(TFModel.getText());
//        } catch (Exception e) {
//            mod.setName("");
//        }
//     mod.setManufacturer(manu);
     
        
        
        
        
        
//           try {
//            
//            for(Manufacturer man : this.manufacturers){
//                if(man.getName().contains(nameManu)){
//                manu = man;
//            }
//            }
//        } catch (Exception e) {
//           manu = new Manufacturer();
//           manu.setName("");
//        }
    }
    
    private void saveColor(){
        
    }
    
    
}
