/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.CarDAO;
import carsalesmanager.model.Car;
import carsalesmanager.model.City;
import carsalesmanager.model.Color;
import carsalesmanager.model.State;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author george
 */
public class MainController implements Initializable {
    
    @FXML
    public TableView <Car> carsTableView;
    @FXML 
    private TableColumn <Car, String> manufacturer;
    @FXML 
    private TableColumn <Car, String> model;
    @FXML 
    private TableColumn <Car, String> color;
    @FXML 
    private TableColumn <Car, String> plate;
    @FXML 
    private TableColumn <Car, String> statusCar;
    @FXML 
    private TableColumn <Car, String> km;
    @FXML 
    private TableColumn <Car, String> carType;
    @FXML 
    private TableColumn <Car, String> price;
    
    
    
    
    
    @FXML
    private Label label;
    
    public ObservableList<Car> cars;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTableView();
                
    }    
    
    
    private void populateTableView(){
        
        CarDAO cDao = new CarDAO(new Car());
       
        this.cars = FXCollections.observableArrayList(cDao.findAllWithoutClose());
      
        this.manufacturer.setCellValueFactory(new Callback<CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Car, String> c) {
                return new SimpleStringProperty(c.getValue().getModel().getManufacturer().getName());                
            }
        });
        this.model.setCellValueFactory(new Callback<CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Car, String> c) {
                return new SimpleStringProperty(c.getValue().getModel().getName());                
            }
        });       
        this.color.setCellValueFactory(new Callback<CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Car, String> c) {
               return new SimpleStringProperty(c.getValue().getColor().getName());                
            }
        }); 
        this.plate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        this.statusCar.setCellValueFactory(new Callback<CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Car, String> c) {
               String value = (c.getValue().getStatusCar() == true) ? "novo": "usado" ;
               return new SimpleStringProperty(value);                
            }
        }); 
        this.km.setCellValueFactory(new PropertyValueFactory<>("km"));
        this.carType.setCellValueFactory(new Callback<CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Car, String> c) {
               return new SimpleStringProperty(c.getValue().getCarType().getName());            
            }
        }); 
        this.price.setCellValueFactory(new Callback<CellDataFeatures<Car, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Car, String> c) {
               return new SimpleStringProperty(" R$ " + c.getValue().getPrice().toString() + "0");            
            }
        }); 
        
        this.carsTableView.setItems(cars);
    }
 
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
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
         
         
         ControllerManager.getInstance().freeze(((Node)(event.getSource())).getScene().getWindow());
        ((Node)(event.getSource())).getScene().getWindow();
         ControllerManager.getInstance().RegisterCar();
    }
}
