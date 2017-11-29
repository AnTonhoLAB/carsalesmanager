/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import carsalesmanager.dao.StateDAO;
import carsalesmanager.model.State;
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
     TFFone.setMask("(NN)NNNN-NNNN");
     TFEmail.setMask("*!@L.*");
     TFNumber.setMask("NNNNNNN");
     
     populeStates();
    }    
    
    
    @FXML
    private void BTSave(ActionEvent event) throws IOException {
     
        
         ControllerManager.getInstance().closeOwner();
         ControllerManager.getInstance().unfreeze();
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
