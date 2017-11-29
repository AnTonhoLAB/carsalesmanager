/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.model.bo;

import carsalesmanager.dao.CarDAO;
import carsalesmanager.dao.SaleDAO;
import carsalesmanager.model.Car;
import carsalesmanager.model.Sale;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author george
 */
public class SaleBO {
    private SaleDAO sDao;

    public SaleBO() {
        this.sDao= new SaleDAO(new Sale());
    }
    
    private void validate(Sale sale)throws Exception{
       if (sale.getOwner().getName() == null)
           throw new Exception("Escolha um comprador");
       for (Car c : sale.getCars()){
           if ( c.getPlate() == null) 
               throw new Exception("Escolha um carro");
       }
       if (sale.getAmount() == 0)
           throw new Exception("Escolha um valor maior que 0 para o veiculo");
       if(sale.getPortionNumber() == 0)
           throw new Exception("Escolha em quantas vez deseja parcelar o carro \n se for pagamento a vista coloque em 1x");
    }
    
    public void Save(Sale sale)throws Exception{
        validate(sale);
        try {
            CarBO cBo = new CarBO();
            sale.setCars(getCar(sale.getCars())); 
            this.sDao.save(sale);
            
            
            
           // changeCar(sale);
            getCar(sale.getCars());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
    }
    
    private Set<Car> getCar(Set<Car> car){
        SaleDAO cd = new SaleDAO(new Sale());
        
        ArrayList<Sale> ss =  (ArrayList<Sale>) cd.findAll();

        Sale mySale = ss.get(ss.size() - 1);
        ArrayList<Car> css= new ArrayList<>(mySale.getCars());  
        
        Car myCar = css.get(0);
        
        System.out.println(myCar.getPlate());
        
        return car;
    }
    
    private void changeCar(Sale sale){
        CarDAO c = new CarDAO(new Car());
        
        ArrayList<Sale> allSales = (ArrayList<Sale>) this.sDao.findAllWithoutClose();
        
     //   ArrayList<Car> allCars = (ArrayList<Car>) c.findAll();
        
        for (Sale allSale : allSales) {
//            ArrayList<Car> allCarSale = (ArrayList<Car>) allSale.getCars();
//            ArrayList<Car> saleCar = (ArrayList<Car>) sale.getCars();
//            
                    System.out.println(allSale.getOwner().getCpf());
                    System.out.println(allSale.getAmount());
                    System.out.println(sale.getOwner().getCpf());
                    System.out.println(sale.getAmount());
                    
            
            if(allSale.getOwner().getCpf().contains(sale.getOwner().getCpf()) && allSale.getAmount() == sale.getAmount()){
                sale = allSale;
                System.out.println("Aquui deu");
            }else{
                System.out.println("nao deu");
            }
                
        }
 
        
           
    }
   
}
