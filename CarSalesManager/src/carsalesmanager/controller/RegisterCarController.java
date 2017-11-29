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
import carsalesmanager.model.bo.CarBO;
import carsalesmanager.util.MaskTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

        try{
            CarBO cbo = new CarBO();
           
            Car car = new Car(getCarType(), getColor(), getModel(), getPlate(), getAge(), getDescription(), getStatus(), getKm(), getPrice(), false, getAccessories());
          
            cbo.save(car);
            
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(e.getMessage());
            alert.setResizable(true);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    
    private CarType getCarType(){
       
        RadioButton chk = (RadioButton)TGType.getSelectedToggle();  
        CarType cType = new CarType();  
        cType.setName(chk.getText());

        return cType;
    }   
    
    private Color getColor(){
        Color color = new Color();
        try {
            color.setName(CBColor.getSelectionModel().getSelectedItem().toString());
        } catch (Exception e) {
            color.setName("");
        }
        return color;
    }

    
    
    private Model getModel() throws Exception{
     
        Manufacturer manuToSave = new Manufacturer();
            try {
                 manuToSave.setName(CBManufacturer.getSelectionModel().getSelectedItem().toString());
       
            } catch (Exception e) {
        manuToSave.setName("");
            }
        Model modToSave = new Model();
            try {
                modToSave.setName(TFModel.getText()); 
            } catch (Exception e) {
                modToSave.setName("");
            }
        
        modToSave.setManufacturer(manuToSave);
        return modToSave;
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
    
    private Set<Accessory> getAccessories(){
        Set<Accessory>  acc = new HashSet<>();
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
        
        for (String string : acString) {
           Accessory accessory = new Accessory();
           accessory.setName(string);
           acc.add(accessory);
        }
    
        Set<Accessory> foo = new HashSet<Accessory>(acc);
        return foo;
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
