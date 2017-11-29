/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;


/**
 * FXML Controller class
 *
 * @author george
 */
public class RegisterOwnerController implements Initializable {

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
    
    @FXML
    private void BTSave(ActionEvent event) throws IOException {
     
        
         ControllerManager.getInstance().closeOwner();
         ControllerManager.getInstance().unfreeze();
    }
}
