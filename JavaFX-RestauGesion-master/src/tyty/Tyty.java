/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyty;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tyty.controller.LoginController;

/**
 *
 * @author dell
 */
public class Tyty extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(LoginController.class.getResource("../view/Login.fxml"));
//                Parent root = FXMLLoader.load(getClass().getResource("/tyty/view/Menu.fxml"));	

        Scene scene = new Scene(root);
      
        
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.setResizable(false);
        stage.getIcons().add(new Image("tyty/images/burger.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
