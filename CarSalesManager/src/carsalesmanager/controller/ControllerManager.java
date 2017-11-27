/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author george
 */
public class ControllerManager extends Application{

    private Window win;
    
    private RegisterCarController registerCar;
    
    
    private static ControllerManager instance;
    
     public static ControllerManager getInstance(){
        if(instance == null)
            instance = new ControllerManager();
           return instance;
    }
    
    public void start(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/carsalesmanager/view/Main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    public void RegisterCar() throws IOException{
        this.registerCar = new RegisterCarController();
    }
    
    public void freeze(Window window){
       this.win = window;
       window.setOpacity(0.7);
    }
    
    public void unfreeze(){
        this.win.setOpacity(1);     
    }
}
