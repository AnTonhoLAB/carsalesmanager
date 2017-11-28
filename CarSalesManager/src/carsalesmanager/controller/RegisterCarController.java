/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.ColorDAO;
import carsalesmanager.dao.ManufacturerDAO;
import carsalesmanager.model.Car;
import carsalesmanager.model.CarType;
import carsalesmanager.model.Color;
import carsalesmanager.model.Manufacturer;
import carsalesmanager.model.Model;
import carsalesmanager.util.MaskTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;


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
    private TextField TFModel;
    @FXML
    private MaskTextField TFAge;
    @FXML
    private MaskTextField TFKm;
    @FXML
    private MaskTextField TFPlate;
    @FXML
    private ToggleGroup TGState;
    @FXML
    private ToggleGroup TGType;
    
    ArrayList<Manufacturer> manufacturers;
    
    
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
        getModel();

        //get status from radio button
         RadioButton chk = (RadioButton)TGState.getSelectedToggle();  
         boolean status = chk.getText().contains("N") ? true : false;     
         System.out.println(status);
          
        try{
          //  System.out.println(this.Estado.getSelectedToggle().getUserData().toString());
            
     //     Car car = new Car(cartype, color, model, sale, plate, age, description, status, km, price, false, accessories)
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(e.toString());
            alert.setResizable(true);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    
    private CarType getCarType(){
        RadioButton chk = (RadioButton)TGType.getSelectedToggle();  
        CarType cType = new CarType();
        
        if(chk.getText().contains("pac")){
            cType.setName("Compacto");
        }else if(chk.getText().contains("porti")){
            cType.setName("Esportivo");
        } else if(chk.getText().contains("sseio")){
            cType.setName("Passeio");
        } else if(chk.getText().contains("dan")){
            cType.setName("Sedan");
        } else if(chk.getText().contains("tario")){
            cType.setName("Ultilitário");
        } else{
            cType.setName("SUV");
        }
        
        System.out.println(cType.getName());
        return cType;
    }   
    
    private Color getColor(){
        Color color = new Color();
        try {
            String name = CBColor.getSelectionModel().getSelectedItem().toString();
        
            color.setName(name);
        } catch (Exception e) {
           color.setName("");
        }
        return color;
    }

    private Model getModel(){
     Model mod = new Model();
     Manufacturer manu = new Manufacturer();
        try {
             String nameManu = CBManufacturer.getSelectionModel().getSelectedItem().toString();
    
             for(Manufacturer man : this.manufacturers){
          if(man.getName().contains(nameManu)){
            manu = man;
          }
        }
        } catch (Exception e) {
           manu = new Manufacturer();
           manu.setName("");
        }
        mod.setName(TFModel.getText());
        mod.setManufacturer(manu);
        System.out.println("MARCA RETORNO");
        System.out.println(mod.getName() + "    " + mod.getManufacturer().getName());
        return mod;
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
        this.manufacturers = (ArrayList<Manufacturer>) mDao.findAll();
        ArrayList<String> manuNames = new ArrayList<>();
        
        for (Manufacturer manu : manufacturers) {
            manuNames.add(manu.getName());
        }
        
        ObservableList obsManus= FXCollections.observableList(manuNames);
        CBManufacturer.getItems().clear();
        CBManufacturer.setItems(obsManus);
    }
    
}
