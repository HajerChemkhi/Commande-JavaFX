/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.commande;

import static DBCnx.MyConnection.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class commandeAddController implements Initializable {

    @FXML
    private Button btnajouter;

    commandeController obj = new commandeController();

    @FXML
    private TextField date_commandetf;
    @FXML
    private TextField date_livraisontf;
    @FXML
    private TextField totaltf;
    @FXML
    private TextField prix_livraisontf;
    @FXML
    private Button btnbrowse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private boolean validateFields() {
        if (date_commandetf.getText().isEmpty()) {
            showAlert("Error", "date_commande field is required.");
            return false;
        }
        if (date_livraisontf.getText().isEmpty()) {
            showAlert("Error", "date_livraison field is required.");
            return false;
        }


        if (totaltf.getText().isEmpty()) {
            showAlert("Error", "total field is required.");
            return false;
        }
          if (prix_livraisontf.getText().isEmpty()) {
            showAlert("Error", "prix field is required.");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void OnCreate(ActionEvent event) throws SQLException {
        if (event.getSource() == btnajouter) {
            if (validateFields()) {
                insert();
                obj.showcommandess();
            }
        }

    }

    private void executeQuery(String query) {
        Connection conn = MyConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }
   

 private void insert() {
    String date_commande = date_commandetf.getText();
    String  date_livraison= date_livraisontf.getText();
    Integer total = Integer.valueOf(totaltf.getText());
    Integer 	prix_livraison = Integer.valueOf(prix_livraisontf.getText());
    validateFields();
    String query = "INSERT INTO `commande`(`date_commande`, `date_livraison`, `total`, `prix_livraison`) VALUES (?, ?, ?, ?)";

    try (Connection conn = MyConnection();
         PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setString(1, date_commande);
        statement.setString(2, date_livraison);
        statement.setInt(3, total);
        statement.setInt(4, prix_livraison);

        statement.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Commande ");
        alert.setHeaderText(null);
        alert.setContentText("added  successfully");
        alert.showAndWait();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    

        }
    


