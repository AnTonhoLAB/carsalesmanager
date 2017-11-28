/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.ColorDAO;
import carsalesmanager.model.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateCBColor();
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
    
}
