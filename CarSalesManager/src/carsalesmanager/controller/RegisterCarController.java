/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.ColorDAO;
import carsalesmanager.dao.ManufacturerDAO;
import carsalesmanager.model.Color;
import carsalesmanager.model.Manufacturer;
import carsalesmanager.util.MaskTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;


/**
 * FXML Controller class
 *
 * @author george
 */
public class RegisterCarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox CBColor;
    @FXML
    private ComboBox CBManufacturer;
    @FXML
    private MaskTextField TFAge;
    @FXML
    private MaskTextField TFKm;
    @FXML
    private MaskTextField TFPlate;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.TFAge.setMask("NNNN");
        this.TFAge.setPromptText("EX: 1999");
        this.TFKm.setMask("N!");
        this.TFPlate.setMask("PPP-NNNN");
        this.TFPlate.setPromptText("EX: QQQ-6666");

        populateCBColor();
        populateCBManufacturer();
    }    
    
    @FXML
    private void BTSave(ActionEvent event) throws IOException {
     
            System.out.println("ARRRIBA");
            System.out.println("ARRRIBA");
            System.out.println("ARRRIBA");
            System.out.println("ARRRIBA");
            
//        ControllerManager.getInstance().freeze(((Node)(event.getSource())).getScene().getWindow());
//         
//         ControllerManager.getInstance().RegisterCar();
    }
    
    
    
    
    
    
    
    
    private void populateCBColor(){
        ColorDAO cDao = new ColorDAO(new Color());
        ArrayList<Color> colors =  (ArrayList<Color>) cDao.findAll();
        ArrayList<String> colorNames = new ArrayList<>();
        
        for (Color color : colors) {
            colorNames.add(color.getName());
        }
      
        ObservableList obsCores = FXCollections.observableList(colorNames);
        CBColor.getItems().clear();
        CBColor.setItems(obsCores);
    }
    
    private void populateCBManufacturer(){
        ManufacturerDAO mDao = new ManufacturerDAO(new Manufacturer());
        ArrayList<Manufacturer> manufacturers = (ArrayList<Manufacturer>) mDao.findAll();
        ArrayList<String> manuNames = new ArrayList<>();
        
        for (Manufacturer manu : manufacturers) {
            manuNames.add(manu.getName());
        }
        
        ObservableList obsManus= FXCollections.observableList(manuNames);
        CBManufacturer.getItems().clear();
        CBManufacturer.setItems(obsManus);
    }
    
}
