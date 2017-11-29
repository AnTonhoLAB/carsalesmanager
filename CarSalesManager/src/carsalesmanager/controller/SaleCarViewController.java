/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.CarDAO;
import carsalesmanager.dao.OwnerDAO;
import carsalesmanager.model.Car;
import carsalesmanager.model.Owner;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author george
 */
public class SaleCarViewController implements Initializable {

    @FXML
    private ComboBox CBOwner;
    @FXML
    private ComboBox CBCar;
    @FXML
    private TextField TFTotal;
    @FXML
    private ComboBox CBParcela; 
    
    ArrayList<Owner> allOwner;
    ArrayList<Car> allCars;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populeOwners();
        populeCars();
      populateParcelas();
    }    
    
     @FXML
    private void BTSave(ActionEvent event) throws IOException, Exception {
    
    
    }
    
    
    private void populeOwners(){
        OwnerDAO oDao = new OwnerDAO(new Owner());
        this.allOwner = (ArrayList<Owner>) oDao.findAll();
        ArrayList<String> OwnerNames = new ArrayList<>();
        
        for (Owner ow : allOwner) {
            OwnerNames.add(ow.getName()+" "+ow.getCpf());
        }
        
        ObservableList obsStates= FXCollections.observableList(OwnerNames);
        CBOwner.getItems().clear();
        CBOwner.setItems(obsStates);
    }

    private void populeCars(){
        CarDAO cDao = new CarDAO(new Car());
        this.allCars = (ArrayList<Car>) cDao.findAllWithoutClose();
        ArrayList<String> CarNames = new ArrayList<>();
        
        for (Car c : allCars) {
            // mostra apenas carros nao comprados
             try {
                 if (c.getSale().getOwner() == null){
                     //dont add car sale
                 }
            } catch (Exception e) {
                CarNames.add(c.getModel().getManufacturer().getName() + " "+ c.getModel().getName()+"  ("+c.getPlate()+")");
            }  
        }
        ObservableList obsCars= FXCollections.observableList(CarNames);
        
        CBCar.getItems().clear();
        CBCar.setItems(obsCars);
        cDao.closeSession();
    }
    
    private void populateParcelas(){
        
    ArrayList<String> parcelas = new ArrayList<>();
    
        for (int i = 1; i <= 55; i++) {
            parcelas.add(i+" X");
        }
        ObservableList obsParse= FXCollections.observableList(parcelas);
        
        CBParcela.getItems().clear();
        CBParcela.setItems(obsParse);
    }

}
