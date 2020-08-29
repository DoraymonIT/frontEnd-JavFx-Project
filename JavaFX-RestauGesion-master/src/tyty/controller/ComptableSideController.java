/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyty.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tyty.bean.Commande;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ComptableSideController implements Initializable {
    
    private LoginController documentController;
    
    public LoginController getDocumentController() {
        return documentController;
    }
    
    public void setDocumentController(LoginController documentController) {
        this.documentController = documentController;
    }
    @FXML
    private TableView<Commande> tableCommandes;
    
    @FXML
    private TableColumn<Commande, Integer> numCmd;
    
    @FXML
    private TableColumn<Commande, Integer> numTable;
    
    @FXML
    private TableColumn<Commande, String> entree;
    
    @FXML
    private TableColumn<Commande, String> plat;
    
    @FXML
    private TableColumn<Commande, String> drink;
    
    @FXML
    private Label date;
    
    @FXML
    private Label adresse;
    
    @FXML
    private Label phone;
    
    @FXML
    private Label entreeDemande;
    
    @FXML
    private Label platDemande;
    
    @FXML
    private Label DrinkDemande;
    
    @FXML
    private Label prixE;
    
    @FXML
    private Label prixP;
    
    @FXML
    private Label prixD;
    
    @FXML
    private Label totalCalculer;
    
    @FXML
    private JFXButton imprimer;
    
    ObservableList<Commande> commandes = FXCollections.observableArrayList();

    private ObservableList<Commande> products() {
        commandes.addAll(new Commande(1, 12, "Entree1", "Plat1", "Drink1"),
                new Commande(2, 8, "Entree2", "Plat2", "Drink2"));
        return commandes;
    }
    
    Commande commandesSelected = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numCmd.setCellValueFactory(new PropertyValueFactory<>("numCmd"));
        numTable.setCellValueFactory(new PropertyValueFactory<>("NumTable"));
        entree.setCellValueFactory(new PropertyValueFactory<>("Entree"));
        plat.setCellValueFactory(new PropertyValueFactory<>("Plat"));
        drink.setCellValueFactory(new PropertyValueFactory<>("Drink"));
        tableCommandes.getItems().addAll(products());
        tableCommandes.setOnMousePressed((MouseEvent event) -> {
            commandesSelected = tableCommandes.getSelectionModel().getSelectedItem();
        });
        
    }

    @FXML
    void detailles(ActionEvent event) {
        if (commandesSelected != null) {
            Date d = new Date();
            date.setText(d.toString());
            adresse.setText("1030,Rue Marrakech");
            prixE.setText("40.00 DH");
            prixP.setText("100.00 DH");
            prixD.setText("30.00 DH");
            totalCalculer.setText("170.00 DH");
            phone.setText("+212-6-66-66-66");
            entreeDemande.setText(commandesSelected.getEntree());
            platDemande.setText(commandesSelected.getPlat());
            DrinkDemande.setText(commandesSelected.getDrink());
        }
    }
     @FXML
    void deconnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginController.class.getResource("../view/Login.fxml"));
            Parent root;
            root = (Parent) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
                    stage.setResizable(false);

            Node node = (Node) event.getSource();
            Stage stage1 = (Stage) node.getScene().getWindow();
            stage1.hide();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminSideController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void setParentController(LoginController aThis) {
        documentController = aThis;
    }
    
}
