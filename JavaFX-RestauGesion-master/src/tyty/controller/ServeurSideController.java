/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyty.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tyty.bean.Commande;

import tyty.bean.Product;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ServeurSideController implements Initializable {

    @FXML
    TableColumn<Commande, Integer> numCmd;
    @FXML
    TableColumn<Commande, Integer> numTable;
    @FXML
    TableColumn<Commande, String> entree;
    @FXML
    TableColumn<Commande, String> plat;
    @FXML
    TableColumn<Commande, String> drink;
//
//    @FXML
//    private Label what;
    ObservableList<Commande> commandes = FXCollections.observableArrayList();

    @FXML
    private JFXTextField numeroTable;

    @FXML
    private JFXComboBox<Product> comboBoxEntree;

    @FXML
    private JFXComboBox<Product> comboBoxPlat;

    @FXML
    private JFXComboBox<Product> comboBoxDrink;

    @FXML
    private TableView<Commande> tableCommandes;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton exitButton;

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

    @FXML
    void enregitrer(ActionEvent e) {

        int c = Integer.parseInt(numeroTable.getText());
//        System.out.println(numeroTable.getText());
        String c1 = comboBoxEntree.getSelectionModel().getSelectedItem().getNom();
        String c2 = comboBoxPlat.getSelectionModel().getSelectedItem().getNom();
        String c3 = comboBoxDrink.getSelectionModel().getSelectedItem().getNom();
        if (e.getSource() == save) {
            if (numeroTable.getText() == null) {
                Alert l = new Alert(Alert.AlertType.WARNING);
                l.setContentText("Veuillez Entrez d abord un numero de table SVP !!");
                l.setTitle("Ouuups !!!");
                l.showAndWait();
            } else {
                commandes.add(new Commande(generateProductsID(), c, c1, c2, c3));
                tableCommandes.getItems().clear();
                tableCommandes.getItems().setAll(products());
                comboBoxEntree.getSelectionModel().clearSelection();
                comboBoxPlat.getSelectionModel().clearSelection();
                comboBoxDrink.getSelectionModel().clearSelection();
                numeroTable.setText("");

            }

        }
    }

    void setParentController(LoginController documentController) {
    }
// @FXML
//    private void initialize() {
//               what.setText(documentController.getUsername().toString());
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*-------------------Entree-------------------- */
        comboBoxEntree.setConverter(new StringConverter<Product>() {
            @Override
            public String toString(Product object) {
                return object.getNom();
            }

            @Override
            public Product fromString(String string) {
                return null;
            }

        });
        comboBoxEntree.setItems(FXCollections.observableArrayList(
                (Product) new Product("Salade1"),
                new Product("Salade2"),
                new Product("Salade3"),
                new Product("Salade4")));
        /*-------------------Plat principale-------------------- */
        comboBoxPlat.setConverter(new StringConverter<Product>() {
            @Override
            public String toString(Product object) {
                return object.getNom();
            }

            @Override
            public Product fromString(String string) {
                return null;
            }

        });
        comboBoxPlat.setItems(FXCollections.observableArrayList(
                (Product) new Product("plat1"),
                new Product("plat2"),
                new Product("plat3")));
        /*-------------------Drinks-------------------- */
        comboBoxDrink.setConverter(new StringConverter<Product>() {
            @Override
            public String toString(Product object) {
                return object.getNom();
            }

            @Override
            public Product fromString(String string) {
                return null;
            }

        });
        comboBoxDrink.setItems(FXCollections.observableArrayList(
                new Product("Drink1"),
                new Product("Drink2"),
                new Product("Drink3")));
        /*--------------Table -----------*/
//        tableCommandes.getItems().setAll((Collection<Product>) commandes());

// Setting table collumn controller variables
        numCmd.setCellValueFactory(new PropertyValueFactory<>("numCmd"));
        numTable.setCellValueFactory(new PropertyValueFactory<>("numTable"));
        entree.setCellValueFactory(new PropertyValueFactory<>("Entree"));
        plat.setCellValueFactory(new PropertyValueFactory<>("Plat"));
        drink.setCellValueFactory(new PropertyValueFactory<>("Drink"));
        tableCommandes.getItems().setAll(products());
    }

    private ObservableList<Commande> products() {

        return commandes;
    }

    int generateProductsID() {
        int a = 1;

        for (Commande o : commandes) {
            if (o.getNumCmd() >= a) {
                a = o.getNumCmd() + 1;
            }
        }

        return a;
    }

}
