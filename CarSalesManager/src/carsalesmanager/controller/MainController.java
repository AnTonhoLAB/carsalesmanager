/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.CarDAO;
import carsalesmanager.model.Car;
import carsalesmanager.model.City;
import carsalesmanager.model.State;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author george
 */
public class MainController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
        
        CarDAO cDao = new CarDAO(new Car());
       
        ArrayList<Car> cars = (ArrayList<Car>) cDao.findAllWithoutClose();
        
        for (Car car : cars) {
            try {
                
                System.out.println(car.getModel().getName());
            //    System.out.println(sDap.findAllWithoutClose());
                
            } catch (Exception e) {
                System.out.println(e.toString());
            }
           
        }
         cDao.closeSession();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
