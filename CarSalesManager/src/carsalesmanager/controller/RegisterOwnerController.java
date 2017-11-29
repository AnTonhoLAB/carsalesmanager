/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.StateDAO;
import carsalesmanager.model.Address;
import carsalesmanager.model.City;
import carsalesmanager.model.Contact;
import carsalesmanager.model.Owner;
import carsalesmanager.model.State;
import carsalesmanager.model.bo.OwnerBO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 * @author george
 */
public class RegisterOwnerController implements Initializable {

   @FXML
   TextField TFName;
   @FXML
   MaskTextField TFFone;
   @FXML
   MaskTextField TFEmail;
   @FXML
   MaskTextField TFNumber;
   @FXML
   MaskTextField TFCPF;
   @FXML
   TextField TFStreet;
   @FXML
   TextField TFDistrict;
   @FXML
   TextField TFCity;
   @FXML
   ComboBox CBState;
   
   ArrayList<State> allStates;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     TFFone.setMask("NNNN-NNNN");
     TFFone.setPromptText("EX: 3490-4304");
     TFNumber.setMask("NNNNNNN");
     TFCPF.setMask("NNN.NNN.NNN-NN");
     TFCPF.setPromptText("EX: 098.098.678-10");
     
     populeStates();
    }    
    
    
    @FXML
    private void BTSave(ActionEvent event) throws IOException, Exception {
        OwnerBO owBo = new OwnerBO();
        try {
            
            Contact c = new Contact(this.TFFone.getText(), this.TFEmail.getText());
            City cit = new City(getState(), TFCity.getText());
            Address a = new Address(cit, TFStreet.getText(), getNumber());
            Set<Contact> contacts = new HashSet<Contact>();
            contacts.add(c);
        
            Owner ow = new Owner(a, TFCPF.getText(), TFName.getText() , contacts);
        
            owBo.save(ow);
        
        
            ControllerManager.getInstance().closeOwner();
            ControllerManager.getInstance().unfreeze();
            
        } catch (Exception e) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(e.getMessage());
            alert.setResizable(true);
            Optional<ButtonType> result = alert.showAndWait();

        }        
    }
    
    private State getState(){
        State st = new State();
        
        try {
            st.setName(CBState.getSelectionModel().getSelectedItem().toString());
        } catch (Exception e) {
            st.setName("");
        }
        return st;
    }
    
    private int getNumber(){
        int toReturn;
        
        try {
          toReturn = Integer.parseInt(TFNumber.getText().toString());
        } catch (Exception e) {
          toReturn = 0;
        }
        
        return toReturn;
    }
    
    private void populeStates(){
        StateDAO sDao = new StateDAO(new State());
        this.allStates = (ArrayList<State>) sDao.findAll();
        ArrayList<String> stateNames = new ArrayList<>();
        
        for (State state : allStates) {
            stateNames.add(state.getName());
        }
        
        ObservableList obsStates= FXCollections.observableList(stateNames);
        CBState.getItems().clear();
        CBState.setItems(obsStates);
    }

}
