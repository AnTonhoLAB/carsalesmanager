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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author george
 */
public class ControllerManager extends Application{

    private Window win;
    
    private RegisterCarController registerCar;
    private RegisterOwnerController registerOwner;
    
    private Stage ownerStage;
    private Stage carStage;
    private Stage saleCarStage;
    
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
            Parent root = FXMLLoader.load(getClass().getResource("/carsalesmanager/view/RegisterCarView.fxml"));
            this.carStage = new Stage();
            
            this.carStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            this.carStage.setScene(scene);
            this.carStage.setResizable(false);
            
            this.carStage.show();
            // configura ação apos a propria janela ser fechada
            this.carStage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
                ControllerManager.getInstance().unfreeze();
                this.carStage.close();
            });
        
    }
    
    public void RegisterOwner() throws IOException{
           
            Parent root = FXMLLoader.load(getClass().getResource("/carsalesmanager/view/RegisterOwner.fxml"));
            this.ownerStage = new Stage();
            
            this.ownerStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            this.ownerStage.setScene(scene);
            this.ownerStage.setResizable(false);
            
            this.ownerStage.show();
            
            // configura ação apos a propria janela ser fechada
            this.ownerStage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
                ControllerManager.getInstance().unfreeze();
                this.ownerStage.close();
            });
    }  
    public void saleCar() throws IOException{
          Parent root = FXMLLoader.load(getClass().getResource("/carsalesmanager/view/SaleCarView.fxml"));
            this.saleCarStage = new Stage();
            
            this.saleCarStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            this.saleCarStage.setScene(scene);
            this.saleCarStage.setResizable(false);
            
            this.saleCarStage.show();
            
            // configura ação apos a propria janela ser fechada
            this.saleCarStage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
                ControllerManager.getInstance().unfreeze();
                this.saleCarStage.close();
            });
    }
    
    public void closeRegisterCar(){
        this.carStage.close();
        this.unfreeze();
    }
    
      public void closeOwner(){
         
         this.ownerStage.close();
         this.unfreeze();
      }
    
    public void freeze(Window window){
       this.win = window;
       window.setOpacity(0.7);
    }
    
    public void unfreeze(){
        this.win.setOpacity(1);     
    }
}
