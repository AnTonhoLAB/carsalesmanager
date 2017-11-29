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
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author george
 */
public class MainController implements Initializable {
     CarDAO cDao;
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
        contextMenu();
    }    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
     
        this.cDao.closeSession();         
        ControllerManager.getInstance().freeze(((Node)(event.getSource())).getScene().getWindow());
         
         ControllerManager.getInstance().RegisterCar();
    }
    
    @FXML
    private void BTAddOwner(ActionEvent event) throws IOException {
     
        this.cDao.closeSession();         
        ControllerManager.getInstance().freeze(((Node)(event.getSource())).getScene().getWindow());
         
         ControllerManager.getInstance().RegisterOwner();
    }
    
    @FXML
    private void BTSale(ActionEvent event) throws IOException {
     
        this.cDao.closeSession();         
      
    }
    
    @FXML
    private void BTReload(ActionEvent event) throws IOException {
     
        populateTableView();
      
    }
    
        public void populateTableView(){
        
        this.cDao = new CarDAO(new Car());
       
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
 
    private void contextMenu(){
       
    // ADD um menu de contexto ao clicar com o botão direito sobre uma célula
    carsTableView.setRowFactory((TableView<Car> param) -> {
        
        final TableRow<Car> row = new TableRow<>();
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem saleMenuItem = new MenuItem("Sale");
        final MenuItem editMenuItem = new MenuItem("Edit");
        final MenuItem removeMenuItem = new MenuItem("Remove");
          // Action do que o botão criao no menu vai fazer
      
        saleMenuItem.setOnAction((ActionEvent event) -> {
           //TODO 
            System.out.println("TODO");
        });  
        editMenuItem.setOnAction((ActionEvent event) -> {
           //TODO
            System.out.println("TODO");
        });
          removeMenuItem.setOnAction((ActionEvent event) -> {
          // TODO
           System.out.println("TODO");
        });
        
        // Add buttons on menu
        contextMenu.getItems().add(saleMenuItem);
        contextMenu.getItems().add(editMenuItem);
        contextMenu.getItems().add(removeMenuItem);
        //  dont show context menu in empty row
        row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu)null).otherwise(contextMenu));
        
        
        return row ;
    });
   }
}
