/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.AccessoryDAO;
import carsalesmanager.dao.ColorDAO;
import carsalesmanager.dao.ManufacturerDAO;
import carsalesmanager.dao.TypeDao;
import carsalesmanager.model.Accessory;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
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
    private CheckBox CBAr;
    @FXML
    private CheckBox CBAlarm;
    @FXML
    private CheckBox CBRoda;
    @FXML
    private CheckBox CBTapete;
    @FXML
    private CheckBox CBFita;
    

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
    @FXML
    private TextArea TADescription;
    
    ArrayList<Manufacturer> manufacturers;
    ArrayList<Color> colors;
    ArrayList<CarType> carTypes;
    ArrayList<Accessory> accessories;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeDao tDao = new TypeDao(new CarType());
        AccessoryDAO aDao = new AccessoryDAO(new Accessory());
        
        this.TFAge.setMask("NNNN");
        this.TFAge.setPromptText("EX: 1999");
        this.TFKm.setMask("NNNNNNNNN");
        this.TFPlate.setMask("PPP-NNNN");
        this.TFPlate.setPromptText("EX: QQQ-6666");
      
        this.carTypes = (ArrayList<CarType>) tDao.findAll();
        this.accessories = (ArrayList<Accessory>) aDao.findAll();
        
        populateCBColor();
        populateCBManufacturer();
    }    
    
    @FXML
    private void BTSave(ActionEvent event) throws IOException {
        
        getAccessories();
        //get status from radio button
         
          
        try{
                Car car = new Car();
     //     Car car = new Car(cartype, color, model, plate, age, description, status, km, price, false, accessories)
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
        
        TypeDao tDao = new TypeDao(new CarType());
        ArrayList<CarType> types = (ArrayList<CarType>) tDao.findAll();
        
        for (CarType type : types) {
            if(type.getName().contains(chk.getText())){
                cType = type;
                System.out.println(cType.getName());
            }
        }
        return cType;
    }   
    
    private Color getColor(){
        Color color = new Color();
        
        try {
            String name = CBColor.getSelectionModel().getSelectedItem().toString();
            
            for (Color col : colors) {
                if(col.getName().contains(name)){
                    color = col;
                }
            }
        } catch (Exception e) {
           color.setName("");
        }
        
        System.out.println(color.getName());
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
 
        return mod;
    }
    
    private String getPlate(){
        return TFPlate.getText().toUpperCase();
    }
            
    private String getDescription(){
        return TADescription.getText();
    }
    
    private boolean getStatus(){
        RadioButton chk = (RadioButton)TGState.getSelectedToggle();  
        return chk.getText().contains("N") ? true : false;     
    }
     
    private int getKm(){
        int toReturn;
        
        try {
            toReturn = Integer.parseInt(TFKm.getText());
        } catch (Exception e) {
            toReturn = -1;
        }
    
        return toReturn;
    }
    
    private int getAge(){
        int toReturn;
        
        try {
           toReturn = Integer.parseInt(TFAge.getText()); 
        } catch (Exception e) {
            toReturn = 0;
        }
 
        return toReturn;
    }
    
    private double getPrice(){
        double toReturn;
        
        try {
            toReturn = Double.parseDouble(TFKm.getText());
        } catch (Exception e) {
            toReturn = 0;
        }
        
        return toReturn;        
    }
    
    private Accessory[] getAccessories(){
        AccessoryDAO aDao = new AccessoryDAO(new Accessory());
        ArrayList<Accessory>  acc = new ArrayList<>();
        ArrayList<String> acString = new ArrayList<>();
        //pega os checkbox que estao selecionados 
        if (CBAlarm.isSelected())
            acString.add("Alarme");
        if (CBAr.isSelected())
            acString.add("Ar");
        if (CBRoda.isSelected())
            acString.add("Roda de liga");
        if (CBTapete.isSelected())
            acString.add("Tapete");
        if (CBFita.isSelected())
            acString.add("Toca Fitas");
        
        System.out.println("começo");
        
        for (String string : acString) {
            System.out.println(string);
        }
        
        System.out.println("fim");
        
        
        return null; 
    }
    
    private void populateCBColor(){
        ColorDAO cDao = new ColorDAO(new Color());
        this.colors =  (ArrayList<Color>) cDao.findAll();
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
