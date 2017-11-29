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
import carsalesmanager.model.Sale;
import carsalesmanager.model.bo.CarBO;
import carsalesmanager.model.bo.SaleBO;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    @FXML
    private Label LBDate;
            
    ArrayList<Owner> allOwner;
    ArrayList<Car> allCars;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      populeOwners();
      populeCars();
      populateParcelas();
      
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      LBDate.setText(dateFormat.format(getDate()));
      
      CBCar.setOnAction(new EventHandler<Event>() {
			public void handle(Event arg0) {
				ArrayList<String> lista2 = new ArrayList<String>();
                                
                                int indexPlate = CBCar.getSelectionModel().getSelectedIndex();
                                double price = allCars.get(indexPlate).getPrice();
                                TFTotal.setText(""+price);
			
			}
		});
    }    
  
    @FXML
    private void BTDone(ActionEvent event) throws IOException, Exception {
        SaleBO sBo = new SaleBO();
        CarBO cBo = new CarBO();
        try {
            Sale s = new Sale(getOwner(), getDate(), getNumeroParcela(), getValue(), getCars());
            sBo.Save(s);
            
            ControllerManager.getInstance().closeSale();
            ControllerManager.getInstance().unfreeze();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(e.getMessage());
            alert.setResizable(true);
            Optional<ButtonType> result = alert.showAndWait();

        }
    }
   
    
    private Owner getOwner(){
        Owner toReturn;
        
        try {
            int indexOwner = CBOwner.getSelectionModel().getSelectedIndex();
            toReturn = allOwner.get(indexOwner);
            
        } catch (Exception e) {
            toReturn = new Owner();
        }
         return toReturn;
    }
    
    private Set<Car> getCars(){
        ArrayList<Car> c = new ArrayList<>();
        try{
            int indexCar = CBCar.getSelectionModel().getSelectedIndex();
            c.add(allCars.get(indexCar));
        }catch (Exception e){
            c.add(new Car());
        }
        Set<Car> set = new HashSet<>(c);
        return set;
    }
    
    private Date getDate(){
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return date;
    }
    
    private int getNumeroParcela(){
        int numero = CBParcela.getSelectionModel().getSelectedIndex();
        return numero + 1;
    }

    private double getValue(){
        double toReturn;
        try {
            toReturn = Double.parseDouble(TFTotal.getText());
        } catch (Exception e) {
            toReturn = 0;
        }
       return  toReturn;
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
