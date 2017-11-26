/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Stage stage;
    
    public RegisterCarController() throws IOException{
        
        
            Parent root = FXMLLoader.load(getClass().getResource("/carsalesmanager/view/RegisterCarView.fxml"));
            this.stage = new Stage();
            
            this.stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            
            this.stage.show();
            // configura ação apos a propria janela ser fechada
            this.stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
                ControllerManager.getInstance().unfreeze();
                this.stage.close();
            });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
